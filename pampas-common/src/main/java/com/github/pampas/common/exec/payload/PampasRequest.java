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

import io.netty.channel.ChannelHandlerContext;
import io.opentracing.Span;
import io.opentracing.Tracer;

import java.util.Map;

/**
 * Created by darrenfu on 18-1-24.
 *
 * @param <T> the type parameter
 * @author: darrenfu
 * @date: 18 -1-24
 */
public interface PampasRequest<T> extends Operation {

    /**
     * Id long.
     *
     * @return the long
     */
    long id();

    /**
     * 请求的key
     *
     * @return the string
     */
    String key();

    /**
     * Timestamp long.
     *
     * @return the long
     */
    long timestamp();

    /**
     * Gets channel handler context.
     *
     * @return the channel handler context
     */
    ChannelHandlerContext channelHandlerContext();

    /**
     * Gets tracer.
     *
     * @return the tracer
     */
    Tracer tracer();

    /**
     * Gets span.
     *
     * @return the span
     */
    Span span();

    /**
     * Gets request.
     *
     * @return the request
     */
    T requestData();


    /**
     * 相对URI (eg. /resty/user/get?name=darrenfu)
     *
     * @return the originUri
     */
    String originUri();

    /**
     * 无参数请求路径 （eg. /resty/user/get）
     *
     * @return the requestPath
     */
    String path();


    /**
     * Parameters map.
     *
     * @return the map
     */
    Map<String, String> parameters();

    /**
     * RestyCommand的请求方式（GET/POST）
     *
     * @return the http method
     */
    String httpMethod();

    /**
     * RestyCommand对应的服务名称
     *
     * @return the service name
     */
    String serviceName();


    /**
     * Is keepalive boolean.
     *
     * @return the boolean
     */
    boolean isKeepalive();


    String shotString();
}
