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

package com.github.df.pampas.common.exec.payload;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.Setter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public class DefaultRequestInfo implements RequestInfo<FullHttpRequest> {

    @Setter
    private ChannelHandlerContext channelHandlerContext;

    @Setter
    private FullHttpRequest requestData;

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getTimestamp() {
        return 0;
    }

    @Override
    public ChannelHandlerContext getChannelHandlerContext() {
        return this.channelHandlerContext;
    }

    @Override
    public Tracer getTracer() {
        return null;
    }

    @Override
    public Span getSpan() {
        return null;
    }

    @Override
    public FullHttpRequest getRequestData() {
        return this.requestData;
    }

    @Override
    public String getUri() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getHttpMethod() {
        return null;
    }

    @Override
    public String getServiceName() {
        return null;
    }

    @Override
    public Method getServiceMethod() {
        return null;
    }

    @Override
    public Type getReturnType() {
        return null;
    }

    @Override
    public Object[] getArgs() {
        return new Object[0];
    }

    @Override
    public boolean isKeepalive() {
        return false;
    }
}
