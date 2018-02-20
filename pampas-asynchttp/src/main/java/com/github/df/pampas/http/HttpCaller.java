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

import com.github.df.pampas.common.discover.ServerInstance;
import com.github.df.pampas.common.exec.Caller;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.asynchttpclient.uri.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

/**
 * Async Http Caller.
 * Base on AsyncHttpClient
 *
 * @author: darrenfu
 * @date: 18-1-24
 */
public class HttpCaller implements Caller<FullHttpRequest, Response> {
    private static final Logger log = LoggerFactory.getLogger(HttpCaller.class);

    private AsyncHttpClient client;

    @Override
    public Response call(FullHttpRequest req, ServerInstance serverInstance) {
        return null;
    }

    @Override
    public CompletableFuture<Response> asyncCall(FullHttpRequest req, ServerInstance serverInstance) {
        final FullHttpRequest httpRequest = req;
        try {
            final AsyncHttpClient httpClient = this.client;
            BoundRequestBuilder requestBuilder = new BoundRequestBuilder(httpClient,
                    httpRequest.method().name(),
                    true);
            //TODO 路由,Filter
            requestBuilder.setUri(Uri.create("http://localhost:9001/test1"));
            requestBuilder.setHeaders(httpRequest.headers());
            requestBuilder.addHeader(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

            if (httpRequest.content() != null && httpRequest.content().isReadable()) {
                //请求body转换为ByteBuffer，并且设置为只读，ByteBuf复用 堆内存中的数据 zero copy
                ByteBuffer readOnlyBuffer = httpRequest.content().nioBuffer().asReadOnlyBuffer();
                requestBuilder.setBody(readOnlyBuffer);
            }
            ListenableFuture<Response> listenableFuture = requestBuilder.execute();
            return listenableFuture.toCompletableFuture();

        } catch (Exception ex) {
            CompletableFuture<Response> exceptionFuture = CompletableFuture.completedFuture(null);
            exceptionFuture.completeExceptionally(ex);
            return exceptionFuture;
        }
    }

}
