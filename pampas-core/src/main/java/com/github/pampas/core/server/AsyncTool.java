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

package com.github.pampas.core.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.asynchttpclient.request.body.Body;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by darrenfu on 18-1-18.
 *
 * @author: darrenfu
 * @date: 18-1-18
 */
public class AsyncTool {


    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void doRelease(ChannelHandlerContext ctx, FullHttpRequest req) {
        if(req.content() != null && req.content().isReadable()){

        }
        ByteBuffer byteBuffer = req.content().nioBuffer();
        byteBuffer.put((byte)12);
//        Body body = req.content();
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        readOnlyBuffer.put((byte) 13);
        System.out.println("1.byteBuffer.array():" + byteBuffer.capacity());
        executorService.submit(() -> {
            try {
                send(ctx, "OK", HttpResponseStatus.OK);
                System.out.println(req.refCnt());
                System.out.println("2.byteBuffer.array():" + byteBuffer.capacity());
                req.release();
                System.out.println(req.refCnt());
                System.out.println("3.byteBuffer.array():" + byteBuffer.capacity());
            } catch (Exception ex) {
                ex.printStackTrace();
            }



        });
    }

    private static void send(ChannelHandlerContext ctx, String context, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
