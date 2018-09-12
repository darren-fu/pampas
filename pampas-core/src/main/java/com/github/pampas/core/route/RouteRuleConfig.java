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
import com.github.pampas.core.route.rule.AbstractRule;
import com.github.pampas.core.route.rule.DubboRule;
import com.github.pampas.core.route.rule.GrpcRule;
import com.github.pampas.core.route.rule.HttpRule;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darrenfu on 18-2-23.
 *
 * @author: darrenfu
 * @date: 18-2-23
 */

@Data
public class RouteRuleConfig implements VersionConfig {

    private String loadBalancer;

    private Boolean stripPrefix;

    private String[] selectors;


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

        return this;
    }


    public RouteRuleConfig addRules(AbstractRule... rules) {
        if (rules == null || rules.length == 0) {
            return this;
        }

        for (AbstractRule rule : rules) {
            if (rule == null) {
                continue;
            }
            if (rule instanceof HttpRule) {
                if (http == null) {
                    http = new ArrayList<>(64);
                }
                http.add((HttpRule) rule);
            }
            if (rule instanceof GrpcRule) {
                if (grpc == null) {
                    grpc = new ArrayList<>(64);
                }
                grpc.add((GrpcRule) rule);
            }
            if (rule instanceof DubboRule) {
                if (dubbo == null) {
                    dubbo = new ArrayList<>(64);
                }
                dubbo.add((DubboRule) rule);
            }
        }
        return this;
    }

}
