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
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public abstract class AbstractPampasExecutor<Q extends Object, R extends Object> implements PampasExecutor<Q, R> {
    private static final Logger log = LoggerFactory.getLogger(AbstractPampasExecutor.class);

    protected abstract void doAfter(String threadName);

    protected abstract Caller<Q, R> getCaller();

    public abstract CompletableFuture<ResponseInfo<R>> doExecute(RequestInfo<Q> req);

    @Override
    public Future<ResponseInfo<R>> execute(RequestInfo<Q> req, Callback<Q, R> callback) {
        Q requestData = req.getRequestData();
        CompletableFuture<ResponseInfo<R>> future = doExecute(req);

       


        String netty_threadName = Thread.currentThread().getName();
        future.thenApply(rsp -> {
            try {
                EventExecutor executor = req.getChannelHandlerContext().executor();
                executor.submit(() -> doAfter(netty_threadName));

                ResponseInfo responseInfo = callback == null ? rsp : callback.onSuccess(req, rsp);
                FullHttpResponse httpResponse = convert(req, responseInfo);
                sendResp(req.getChannelHandlerContext(), httpResponse, false);
                return responseInfo;
            } catch (Exception ex) {

                ex.printStackTrace();
                return rsp;
            } finally {
                ReferenceCountUtil.release(req);
            }
        }).exceptionally(ex -> {
            try {
                return callback == null ? ResponseInfo.OK_RESP : callback.onException(req, ex);
            } finally {
                ReferenceCountUtil.release(req);
            }
        });
        return future;
    }


    private FullHttpResponse convert(RequestInfo requestInfo, ResponseInfo responseInfo) {
        FullHttpRequest requestData = (FullHttpRequest) requestInfo.getRequestData();
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(requestData.getProtocolVersion(), HttpResponseStatus.OK,
                //response.getResponseBodyAsByteBuffer是HeapByteBuf
                // zero-copy 设置FullHttpResponse的body
                Unpooled.wrappedBuffer(responseInfo.getResponseData().toString().getBytes()));
//        fullHttpResponse.headers().set(requestData.headers());
        fullHttpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, responseInfo.getResponseData().toString().getBytes().length);
        return fullHttpResponse;
    }

    private void sendResp(ChannelHandlerContext ctx, Object resp, boolean keepalive) {
        if (keepalive) {
            ctx.writeAndFlush(resp);
        } else {
            ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
        }
    }


}
