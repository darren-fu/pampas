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

package com.github.pampas.core.route;

import com.github.df.pampas.common.extension.SpiMeta;
import com.github.df.pampas.common.route.Locator;
import com.github.df.pampas.common.route.Selector;
import com.github.df.pampas.common.tools.RequestTools;
import io.netty.handler.codec.http.HttpRequest;

/**
 * 针对Header的选择器
 * Created by darrenfu on 18-2-26.
 *
 * @author: darrenfu
 * @date: 18-2-26
 */
@SpiMeta(name = "header-selector", order = 10)
public class HttpHeaderSelector implements Selector<HttpRequest> {

    private static final String DEFAULT_HEADER_NAME = "P-SRV-NAME";

    public String header;

    public HttpHeaderSelector() {
        this(DEFAULT_HEADER_NAME);
    }

    public HttpHeaderSelector(String header) {
        this.header = header;
    }


    @Override
    public boolean isMatch(HttpRequest request) {
        return request.headers().contains(this.header);
    }

    @Override
    public Locator select(HttpRequest request) {
        return new Locator(request.headers().get(this.header),
                RequestTools.getPathInUri(request.uri()));
    }


    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.github.df.pampas.common.route.Selector");


    }
}
