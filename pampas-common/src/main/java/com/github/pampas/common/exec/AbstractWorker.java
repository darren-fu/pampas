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

package com.github.pampas.common.exec;

import com.github.pampas.common.exception.PampasException;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.tools.ResponseTools;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 请求执行器抽象类
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public abstract class AbstractWorker<Q extends HttpRequest, R extends Object> implements Worker<Q, R> {

    private static final Logger log = LoggerFactory.getLogger(AbstractWorker.class);

    protected abstract void doAfter(String threadName);

    public abstract CompletableFuture<PampasResponse<R>> doExecute(PampasRequest<Q> req) throws IOException;

    @Override
    public Future<PampasResponse<R>> execute(PampasRequest<Q> req, Filter<Q, R> filter) {

        CompletableFuture<PampasResponse<R>> future = null;
        try {
            future = doExecute(req);
        } catch (Exception e) {
            throw new PampasException(e.getMessage());
        }

        String nettyThreadname = Thread.currentThread().getName();
        future.thenApply(rsp -> {
            try {
                PampasResponse pampasResponse = filter == null ? rsp : filter.onSuccess(req, rsp);
                if (rsp.success()) {
                    log.debug("成功获取响应:{}", pampasResponse);
                    sendResp(req.channelHandlerContext(), pampasResponse.responseData(), req.isKeepalive());
                } else {
                    log.error("Abstract Worker response failed", rsp.exception());
                    sendResp(req.channelHandlerContext(), rsp.exception().getMessage(), req.isKeepalive());
                }

                EventExecutor executor = req.channelHandlerContext().executor();
                executor.submit(() -> doAfter(nettyThreadname));
                return pampasResponse;
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
                return filter == null ? PampasResponse.OK_RESP : filter.onException(req, ex);
            } finally {
                ReferenceCountUtil.release(req);
            }
        });
        return future;
    }

    private void sendResp(ChannelHandlerContext ctx, Object resp, boolean keepalive) {
        HttpResponse objToFlush = ResponseTools.buildResponse(resp);
        if (keepalive) {
            ctx.writeAndFlush(objToFlush);
        } else {
            ctx.writeAndFlush(objToFlush).addListener(ChannelFutureListener.CLOSE);
        }
    }


}
