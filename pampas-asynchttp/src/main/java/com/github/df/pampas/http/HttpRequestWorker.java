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

package com.github.df.pampas.http;

import com.github.df.pampas.common.exec.AbstractWorker;
import com.github.df.pampas.common.exec.payload.DefaultPampasResponse;
import com.github.df.pampas.common.exec.payload.PampasRequest;
import com.github.df.pampas.common.exec.payload.PampasResponse;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.asynchttpclient.Response;

import java.util.concurrent.CompletableFuture;

/**
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public class HttpRequestWorker extends AbstractWorker<FullHttpRequest, FullHttpResponse> {
    HttpCaller caller = new HttpCaller();

    public HttpRequestWorker() {
    }


    @Override
    protected void doAfter(String name) {
        System.out.println("HttpRequestWorker:" + Thread.currentThread().getName());
    }

    @Override
    public CompletableFuture<PampasResponse<FullHttpResponse>> doExecute(PampasRequest<FullHttpRequest> req) {

        FullHttpRequest requestData = req.requestData();

        CompletableFuture<Response> future = caller.asyncCall(requestData, null);
        CompletableFuture<PampasResponse<FullHttpResponse>> responseFuture = future.thenApply(response -> {
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(requestData.protocolVersion(), HttpResponseStatus.valueOf(response.getStatusCode()),
                    //response.getResponseBodyAsByteBuffer是HeapByteBuf
                    // zero-copy 设置FullHttpResponse的body
                    Unpooled.wrappedBuffer(response.getResponseBodyAsByteBuffer()));
            fullHttpResponse.headers().set(response.getHeaders());
            DefaultPampasResponse<FullHttpResponse> defaultResponseInfo = new DefaultPampasResponse();
            defaultResponseInfo.setResponseData(fullHttpResponse);
            return (PampasResponse<FullHttpResponse>) defaultResponseInfo;
        }).exceptionally(ex -> {
            DefaultPampasResponse<FullHttpResponse> defaultResponseInfo = new DefaultPampasResponse();
            defaultResponseInfo.setException(ex);
            defaultResponseInfo.setSuccess(false);
            return defaultResponseInfo;
        });

        return responseFuture;
    }


}
