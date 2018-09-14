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

package com.github.pampas.bootstrap.mock;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.core.route.RouteRuleConfig;
import com.github.pampas.core.route.rule.HttpRule;

/**
 * Created by darrenfu on 18-7-2.
 *
 * @author: darrenfu
 * @date: 18-7-2
 */
@SpiMeta(name = "loader-mock-route-rule", key = PampasConsts.ConfigLoaderKey.ROUTE_RULE)
public class MockRouteRuleConfigLoader extends AbstractConfigLoader<RouteRuleConfig> {

    @Override
    public RouteRuleConfig doConfigLoad() {

        RouteRuleConfig config = new RouteRuleConfig();
        config.setStripPrefix(false);
        config.setLoadBalancer(PampasConsts.LoadBalancer.RANDOM);
        config.addRules(new HttpRule("TestService", "/test", "/test_mapped"));
        return config;
    }

    @Override
    public Class<RouteRuleConfig> configClass() {
        return RouteRuleConfig.class;
    }
}
