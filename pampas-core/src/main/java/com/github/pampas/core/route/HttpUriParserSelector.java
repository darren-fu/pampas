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

import com.github.df.pampas.common.config.Configurable;
import com.github.df.pampas.common.config.VersionConfig;
import com.github.df.pampas.common.extension.SpiMeta;
import com.github.df.pampas.common.route.Locator;
import com.github.df.pampas.common.route.Selector;
import io.netty.handler.codec.http.HttpRequest;

import static com.github.pampas.core.route.HttpUriParserSelector.kEY_SELECTOR;

/**
 * 针对URI的选择器
 * URI: /service_a/user/get/123 needStripServiceName:true
 * -> serviceName:service_a;
 * -> mappedPath:/user/get/123
 * Created by darrenfu on 18-3-4.
 *
 * @author: darrenfu
 * @date: 18-3-4
 */
@SpiMeta(name = kEY_SELECTOR, order = 100)
public class HttpUriParserSelector implements Selector<HttpRequest>, Configurable {

    protected static final String kEY_SELECTOR = "uri-selector";

    //是否去除URI中的serviceName
    private boolean needStripServiceName;


    public HttpUriParserSelector() {
        this(true);
    }

    public HttpUriParserSelector(boolean needStripServiceName) {
        this.needStripServiceName = needStripServiceName;
    }

    @Override
    public boolean isMatch(HttpRequest request) {
        return true;
    }

    @Override
    public Locator select(HttpRequest request) {
        return null;
    }


    public boolean isNeedStripServiceName() {
        return needStripServiceName;
    }

    public void setNeedStripServiceName(boolean needStripServiceName) {
        this.needStripServiceName = needStripServiceName;
    }

    @Override
    public String getKey() {
        return kEY_SELECTOR;
    }

    @Override
    public VersionConfig defaultConfig() {
        return null;
    }

    @Override
    public VersionConfig reloadConfig() {
        return null;
    }

    @Override
    public void setupWithConfig(VersionConfig versionConfig) {

    }


}
