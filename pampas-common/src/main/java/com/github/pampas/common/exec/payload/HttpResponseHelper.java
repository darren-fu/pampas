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

package com.github.pampas.common.exec.payload;

import com.github.pampas.common.exception.PampasException;
import com.github.pampas.common.tools.JsonTools;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by darrenfu on 18-2-5.
 *
 * @author: darrenfu
 * @date: 18 -2-5
 */
public class HttpResponseHelper {
    public static final String TEXT_PLAIN = "text/plain;charset=UTF-8";
    public static final String JSON_TYPE = "application/json;charset=UTF-8";
    public static HttpResponse EMPTY;
    public static HttpResponse OK;

    static {
        EMPTY = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        EMPTY.headers().add(HttpHeaderNames.CONTENT_LENGTH, 0);
        EMPTY.headers().add(HttpHeaderNames.CONTENT_TYPE, TEXT_PLAIN);

    }

    /**
     * 发送Http的响应
     *
     * @param ctx
     * @param resp
     * @param keepalive
     */
    public static void sendHttpResponse(ChannelHandlerContext ctx, Object resp, boolean keepalive) {
        HttpResponse objToFlush = HttpResponseHelper.buildHttpResponse(resp);
        if (keepalive) {
            ctx.writeAndFlush(objToFlush);
        } else {
            ctx.writeAndFlush(objToFlush).addListener(ChannelFutureListener.CLOSE);
        }
    }


    /**
     * 使用文本构建简单的HTTP响应
     *
     * @param obj the obj
     * @return the http response
     */
    public static HttpResponse buildHttpResponse(Object obj) {
        if (obj == null) {
            return EMPTY;
        }
        if (obj instanceof HttpResponse) {
            return (HttpResponse) obj;
        }
        if (obj instanceof String) {
            return buildHttpTextResponse((String) obj, HttpResponseStatus.OK, TEXT_PLAIN);
        }
        if (obj instanceof PampasResponse) {
            return PampasResponseHelper.convertPampasResponseToHttpResponse((PampasResponse) obj);
        }

        if (obj instanceof PampasException) {
            PampasException pampasException = (PampasException) obj;
            return buildHttpTextResponse(pampasException.getMessage(), pampasException.getStatus(), TEXT_PLAIN);
        }

        if (obj instanceof Throwable) {
            Throwable throwable = (Throwable) obj;
            return buildHttpTextResponse(throwable.getMessage(), HttpResponseStatus.BAD_GATEWAY, TEXT_PLAIN);
        }

        //其他情况
        return buildHttpTextResponse(JsonTools.NON_EMPTY.toJson(obj), HttpResponseStatus.OK, JSON_TYPE);
    }


    /**
     * 快速创建一个HTTP响应
     *
     * @param content
     * @param status
     * @param contentType
     * @return HTTP响应
     */
    public static HttpResponse buildHttpTextResponse(String content, HttpResponseStatus status, String contentType) {
        if (StringUtils.isEmpty(content)) {
            HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            httpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, 0);
            httpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, contentType);
            return httpResponse;
        } else {
            ByteBuf copiedBuffer = Unpooled.copiedBuffer(content.getBytes(CharsetUtil.UTF_8));
            HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, copiedBuffer);
            httpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, copiedBuffer.readableBytes());
            httpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, contentType);
            return httpResponse;
        }

    }


}
