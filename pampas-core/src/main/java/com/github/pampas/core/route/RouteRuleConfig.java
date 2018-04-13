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

import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.route.Selector;
import com.github.pampas.common.tools.CollectionTools;
import com.github.pampas.core.route.rule.DubboRule;
import com.github.pampas.core.route.rule.HttpRule;
import com.github.pampas.core.route.rule.GrpcRule;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by darrenfu on 18-2-23.
 *
 * @author: darrenfu
 * @date: 18-2-23
 */

public class RouteRuleConfig implements VersionConfig {

    public boolean stripPrefix;

    public String[] selectors;


    private List<HttpRule> http;

    private List<GrpcRule> grpc;

    private List<DubboRule> dubbo;


    @Override
    public BigDecimal getVersion() {
        return null;
    }

    @Override
    public int compareWith(VersionConfig otherVersionConfig) {
        return 0;
    }

    @Override
    public VersionConfig setupWithDefault() {
        this.stripPrefix = true;
        SpiContext<Selector> selectorSpiContext = SpiContext.getContext(Selector.class);

        List<Class<Selector>> selectorClzList = selectorSpiContext.getSpiClasses(null);

        List<String> spiNameList = selectorClzList.stream().map(v -> SpiContext.getSpiName(v)).collect(Collectors.toList());

        this.selectors = CollectionTools.toArray(spiNameList, String.class);
        return this;
    }


    public static void main(String[] args) {
        SpiContext<Selector> selectorSpiContext = SpiContext.getContext(Selector.class);

        List<Class<Selector>> selectorClzList = selectorSpiContext.getSpiClasses(null);


        List<String> spiNameList = selectorClzList.stream().map(v -> SpiContext.getSpiName(v)).collect(Collectors.toList());
        String[] strings = CollectionTools.toArray(spiNameList, String.class);
        for (String string : strings) {
            System.out.println("->" + string);
        }
        System.out.println(strings.toString());
        System.out.println(CollectionTools.toArray(spiNameList, String.class));
    }
}
