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
     * @param msg the msg
     * @return the http response
     */
    public static HttpResponse buildResponse(String msg) {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.BAD_GATEWAY,
                Unpooled.wrappedBuffer(msg.getBytes()));
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, msg.getBytes().length);
        return response;
    }
}
