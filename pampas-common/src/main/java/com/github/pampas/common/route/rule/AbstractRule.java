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

package com.github.pampas.common.route.rule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.tools.AntPathMatcher;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by darrenfu on 18-3-14.
 *
 * @author: darrenfu
 * @date: 18 -3-14
 */

@Data
public abstract class AbstractRule {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Getter
    @JsonIgnore
    private volatile boolean inited = false;


    private RuleTypeEnum type;
    /**
     * rule对应的service
     */
    private String service;

    /**
     * rule可匹配的请求path
     */
    @Getter
    @Setter
    private String path;

    /**
     * path匹配策略
     */
    private MappingStrategyEnum mappingStrategy;

    /**
     * 匹配的path
     */
    private String mappingPath;

    /**
     * 服务地址匹配策略
     */
    private HostStrategyEnum hostStrategy;

    /**
     * 服务地址
     */
    private String mappingHost;

    @JsonIgnore
    private Object mutex = new Object();

    /**
     * 路由规则类型 H
     *
     * @return rule type enum
     * @see RuleTypeEnum
     */
    @JsonIgnore
    public RuleTypeEnum ruleType() {
        return this.type;
    }


    @Getter
    @JsonIgnore
    private volatile Boolean isAntPath;

    /**
     * rule可匹配的header name
     */
    @Getter
    @Setter
    private String headerName;


    /**
     * rule可匹配的header value
     */
    @Getter
    @Setter
    private String headerValue;

    public AbstractRule() {
    }


    protected AntPathMatcher antPathMatcher() {
        return antPathMatcher;
    }


    public void init() {
        synchronized (mutex) {
            if (inited) {
                return;
            }
            if (StringUtils.isNotEmpty(path)) {
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }

                if (mappingStrategy == MappingStrategyEnum.APPOINT) {

                }
                if (mappingStrategy == MappingStrategyEnum.STRIP) {
                    this.mappingPath = this.path.replaceFirst(this.mappingPath, "");
                }
            }

            if (this.isAntPath == null) {
                this.isAntPath = antPathMatcher().isPattern(path);
            }
            this.inited = true;
        }

    }


    /**
     * 校验请求是否匹配此规则
     *
     * @param request the request
     * @return boolean
     */
    public boolean isMatch(PampasRequest request) {
        if (StringUtils.isEmpty(path) || request == null || request.requestData() == null) {
            return false;
        }
        if (!(request.requestData() instanceof HttpRequest)) {
            return false;
        }
        if (!inited) {
            init();
        }
        return checkMatch(request);
    }


    /**
     * 校验HTTP Request 是否匹配 Rule
     *
     * @param request the request
     * @return the boolean
     */
    protected abstract boolean checkMatch(PampasRequest<FullHttpRequest> request);

}
