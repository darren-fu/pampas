/*
 *
 *  *  Copyright 2009-2018.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.github.df.pampas.common.exec;

import com.github.df.pampas.common.exec.payload.RequestInfo;
import com.github.df.pampas.common.exec.payload.ResponseInfo;
import com.github.df.pampas.common.tools.ResponseTools;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 请求执行器抽象类
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public abstract class AbstractWorker<Q extends Object, R extends Object> implements Worker<Q, R> {

    private static final Logger log = LoggerFactory.getLogger(AbstractWorker.class);

    protected abstract void doAfter(String threadName);


    public abstract CompletableFuture<ResponseInfo<R>> doExecute(RequestInfo<Q> req);

    @Override
    public Future<ResponseInfo<R>> execute(RequestInfo<Q> req, Callback<Q, R> callback) {

        System.out.println("req.getUri(): " + req.getUri());
        CompletableFuture<ResponseInfo<R>> future = doExecute(req);
        ;
        String netty_threadName = Thread.currentThread().getName();
        future.thenApply(rsp -> {
            try {
                ResponseInfo responseInfo = callback == null ? rsp : callback.onSuccess(req, rsp);
                if (rsp.success()) {
                    sendResp(req.getChannelHandlerContext(), responseInfo.responseData(), req.isKeepalive());
                } else {
                    log.error("Abstract Worker response failed", rsp.exception());
                    HttpResponse httpResponse = ResponseTools.buildResponse(rsp.exception().getMessage());
                    sendResp(req.getChannelHandlerContext(), httpResponse, req.isKeepalive());
                }

                EventExecutor executor = req.getChannelHandlerContext().executor();
                executor.submit(() -> doAfter(netty_threadName));
                return responseInfo;
            } catch (Exception ex) {
                log.error("Abstract Worker thenApply error", ex);
                ex.printStackTrace();
                return rsp;
            } finally {
                ReferenceCountUtil.release(req);
            }
        }).exceptionally(ex -> {
            log.error("Abstract Worker error", ex);
            try {
                return callback == null ? ResponseInfo.OK_RESP : callback.onException(req, ex);
            } finally {
                ReferenceCountUtil.release(req);
            }
        });
        return future;
    }

    protected void sendResp(ChannelHandlerContext ctx, Object resp, boolean keepalive) {
        if (keepalive) {
            ctx.writeAndFlush(resp);
        } else {
            ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
        }
    }


}
