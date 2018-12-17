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

package com.github.pampas.common.tools;

import com.github.pampas.common.exception.PampasException;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by darrenfu on 18-2-5.
 *
 * @author: darrenfu
 * @date: 18 -2-5
 */
public class ResponseTools {
    public static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
    public static final String JSON_TYPE = "application/json;charset=UTF-8";
    public static HttpResponse EMPTY;
    public static HttpResponse OK;

    static {
        EMPTY = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        EMPTY.headers().add(HttpHeaderNames.CONTENT_LENGTH, 0);
        EMPTY.headers().add(HttpHeaderNames.CONTENT_TYPE, TEXT_PLAIN);

    }


    public static void sendResp(ChannelHandlerContext ctx, Object resp, boolean keepalive) {
        HttpResponse objToFlush = ResponseTools.buildResponse(resp);
        if (keepalive) {
            ctx.writeAndFlush(objToFlush);
        } else {
            ctx.writeAndFlush(objToFlush).addListener(ChannelFutureListener.CLOSE);
        }
    }

    public static void sendResp(ChannelHandlerContext ctx, HttpResponseStatus status, String context) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, TEXT_PLAIN);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 使用文本构建简单的HTTP响应
     *
     * @param obj the obj
     * @return the http response
     */
    public static HttpResponse buildResponse(Object obj) {
        if (obj == null) {
            return EMPTY;
        }
        if (obj instanceof HttpResponse) {
            return (HttpResponse) obj;
        }
        if (obj instanceof String) {
            String content = (String) obj;
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(content, CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, TEXT_PLAIN);
            return response;
        }
        if (obj instanceof Throwable) {
            if (obj instanceof PampasException) {
                PampasException pampasException = (PampasException) obj;
                byte[] respBytes = pampasException.getMessage().getBytes();
                FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                        pampasException.getStatus(), Unpooled.wrappedBuffer(respBytes));
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, respBytes.length);
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, TEXT_PLAIN);
                return fullHttpResponse;
            } else {
                Throwable throwable = (Throwable) obj;
                byte[] respBytes = throwable.getMessage().getBytes();
                FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                        HttpResponseStatus.BAD_GATEWAY, Unpooled.wrappedBuffer(respBytes));
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, respBytes.length);
                fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, TEXT_PLAIN);
                return fullHttpResponse;
            }

        }
        //其他情况
        byte[] respBytes = JsonTools.NON_EMPTY.toJson(obj).getBytes();
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.wrappedBuffer(respBytes));
        fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, respBytes.length);
        fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, JSON_TYPE);
        return fullHttpResponse;
    }


}
