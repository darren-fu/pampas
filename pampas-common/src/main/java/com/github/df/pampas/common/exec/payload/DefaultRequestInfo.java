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
import io.netty.handler.codec.http.HttpHeaders;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    private String uri;

    private String path;

    private Map<String, String> parameters;

    @Setter
    private String serviceName;


    public DefaultRequestInfo(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) {
        this.channelHandlerContext = ctx;
        this.requestData = fullHttpRequest;
        unpack(this.requestData.getUri());
    }


    @Override
    public long id() {
        return 0;
    }

    @Override
    public String key() {
        return null;
    }

    @Override
    public long timestamp() {
        return 0;
    }

    @Override
    public ChannelHandlerContext channelHandlerContext() {
        return this.channelHandlerContext;
    }

    @Override
    public Tracer tracer() {
        return null;
    }

    @Override
    public Span span() {
        return null;
    }

    @Override
    public FullHttpRequest requestData() {
        this.requestData.content();
        return this.requestData;
    }

    @Override
    public String uri() {
        return this.requestData.getUri();
    }

    @Override
    public String path() {
        return this.path;
    }

    @Override
    public Map<String, String> parameters() {
        return this.parameters;
    }

    @Override
    public String httpMethod() {
        return this.requestData.getMethod().name();
    }

    @Override
    public String serviceName() {
        return serviceName;
    }


    @Override
    public boolean isKeepalive() {
        return HttpHeaders.isKeepAlive(requestData);
    }


    private void unpack(String uri) {
        this.uri = uri;
        int i = uri.indexOf("?"); // seperator between body and parameters
        if (i >= 1) {
            this.parameters = new HashMap<String, String>();

            String[] parts = uri.substring(i + 1).split("\\&");

            for (String part : parts) {
                part = part.trim();
                if (part.length() > 0) {
                    int j = part.indexOf('=');
                    if (j >= 0) {
                        parameters.put(part.substring(0, j), part.substring(j + 1));
                    } else {
                        parameters.put(part, part);
                    }
                }
            }
            this.path = uri.substring(0, i);
        } else {
            this.parameters = Collections.EMPTY_MAP;
            this.path = uri;

        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }
}
