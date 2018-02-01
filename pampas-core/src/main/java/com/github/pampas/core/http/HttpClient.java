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

package com.github.pampas.core.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;
import org.asynchttpclient.*;
import org.asynchttpclient.uri.Uri;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;

/**
 * Http Client Config 包装类
 * Created by darrenfu on 17-6-24.
 */
@SuppressWarnings("Duplicates")
@Data
public class HttpClient {

    private AsyncHttpClient client;

    private AsyncHttpClientConfig config;

    public HttpClient() {
        this.config = HttpConfigFactory.createConfig(30000, 50000);
        this.client = new DefaultAsyncHttpClient(this.config);
    }

    public HttpClient(AsyncHttpClientConfig config) {
        this.client = new DefaultAsyncHttpClient(config);
        this.config = config;
    }

    public HttpClient(AsyncHttpClient client, AsyncHttpClientConfig config) {
        this.client = client;
        this.config = config;
    }


    public void exec() {
        final AsyncHttpClient httpClient = this.client;
        BoundRequestBuilder requestBuilder = new BoundRequestBuilder(httpClient, "GET", true);
        requestBuilder.setUri(Uri.create("http://localhost:9000/test"));
//        requestBuilder.setSingleHeaders();
//        requestBuilder.setBody(requestTemplate.getBody(args));

        ListenableFuture<Response> future = requestBuilder.execute();
        Response response = null;
        try {
            response = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("response:" + response);
        future.done();
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getBody(FullHttpRequest request) {
        ByteBuf buf = request.content();
        return buf.toString(CharsetUtil.UTF_8);
    }

    @Data
    @AllArgsConstructor
    public static class User implements Serializable{

        private String name;

        private Integer age;
    }

    public void exec(ChannelHandlerContext ctx, FullHttpRequest req, Tracer tracer, Span span) {
        try {


            final AsyncHttpClient httpClient = this.client;
            BoundRequestBuilder requestBuilder = new BoundRequestBuilder(httpClient, "GET", true);
            requestBuilder.setUri(Uri.create("http://localhost:9001/test1"));
            requestBuilder.setHeaders(req.headers());
            requestBuilder.addHeader(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

            if (req.content() != null && req.content().isReadable()) {
                //请求body转换为ByteBuffer，并且设置为只读，ByteBuf复用 堆内存中的数据
                ByteBuffer readOnlyBuffer = req.content().nioBuffer().asReadOnlyBuffer();
                requestBuilder.setBody(readOnlyBuffer);

            }
            System.out.println("exec thread:" + Thread.currentThread().getName());

            ListenableFuture<Response> listenableFuture = requestBuilder.execute(new AsyncCompletionHandler<Response>() {
                @Override
                public State onHeadersReceived(final HttpResponseHeaders headers) throws Exception {
                    System.out.println("onHeadersReceived thread:" + Thread.currentThread().getName());

                    return super.onHeadersReceived(headers);
                }

                @Override
                public Response onCompleted(Response response) throws Exception {
                    System.out.println("onCompleted thread:" + Thread.currentThread().getName());

                    User user = new User("testUser", 12);
                    byte[] bytes = SerializationUtils.serialize(user);


                    ByteBuffer buffer = response.getResponseBodyAsByteBuffer();
                    System.out.println("rep buffer:" + buffer.getClass());
                    ByteBuf byteBuf = Unpooled.wrappedBuffer(response.getResponseBodyAsByteBuffer());
                    System.out.println("response byteBuf:" + byteBuf.isDirect());
                    FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                            HttpResponseStatus.BAD_REQUEST,
                            //response.getResponseBodyAsByteBuffer是HeapByteBuf
                            // zero-copy 设置FullHttpResponse的body
//                            Unpooled.wrappedBuffer(response.getResponseBodyAsByteBuffer())
                            Unpooled.wrappedBuffer(bytes)
                    );
                    fullHttpResponse.headers().set(response.getHeaders());
                    fullHttpResponse.headers().set(HttpHeaders.Names.CONTENT_LENGTH, fullHttpResponse.content().capacity());
//                    fullHttpResponse.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
                    fullHttpResponse.headers().set(HttpHeaders.Names.CONTENT_TYPE, response.getContentType());
                    if (HttpHeaders.isKeepAlive(req)) {
                        ctx.write(fullHttpResponse);
                        ctx.flush();
                    } else {
                        ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                    }
                    span.finish();
                    ReferenceCountUtil.release(req);
                    return response;
                }

                @Override
                public void onThrowable(Throwable t) {
                    System.out.println("httpclient-exception:" + t.getMessage());
                    System.out.println("httpclient--" + req.hashCode());
                    System.out.println("httpclient--" + req.getUri());
//                    System.out.println("httpclient--" + getBody(req));
                    send(ctx, t.getMessage(), HttpResponseStatus.BAD_REQUEST);


                    ReferenceCountUtil.release(req);

                }

            });
            listenableFuture.toCompletableFuture().thenAccept((resp) -> {
                System.out.println("listenableFuture:" + resp.getContentType());
            }).exceptionally((ex) -> {
                System.out.println("listenableFuture ex:" + ex);
                return null;
            });
            listenableFuture.addListener(() -> {
                System.out.println("listenableFuture threads:" + Thread.currentThread().getName());
            }, null);

        } catch (Exception ex) {
            System.out.println("exXXXXXXXXXXXXX");
            ex.printStackTrace();
        }
    }

    private static void send(ChannelHandlerContext ctx, String context, io.netty.handler.codec.http.HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                status,
                Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private static void send(ChannelHandlerContext ctx, Response resp, io.netty.handler.codec.http.HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                status,
                //response.getResponseBodyAsByteBuffer是HeapByteBuf
                // zero-copy 设置FullHttpResponse的body
                Unpooled.wrappedBuffer(resp.getResponseBodyAsByteBuffer()));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    public static void main(String[] args) {
        HttpClient clientWrapper = new HttpClient();
        clientWrapper.exec();
        System.out.println("done!!!");
    }
}
