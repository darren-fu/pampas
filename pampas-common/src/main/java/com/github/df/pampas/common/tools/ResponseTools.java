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

package com.github.df.pampas.common.tools;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;

/**
 * Created by darrenfu on 18-2-5.
 *
 * @author: darrenfu
 * @date: 18 -2-5
 */
public class ResponseTools {

    /**
     * 使用文本构建简单的HTTP响应
     *
     * @param obj the obj
     * @return the http response
     */
    public static HttpResponse buildResponse(Object obj) {

        if (!(obj instanceof HttpResponse)) {
            byte[] respBytes = JsonTools.defaultMapper().toJson(obj).getBytes();
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(respBytes));
            fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_LENGTH, respBytes.length);
            fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            return fullHttpResponse;
        }
        return (HttpResponse) obj;
    }


}
