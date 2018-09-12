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

package com.github.pampas.core.route.rule;

import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.tools.AntPathMatcher;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by darrenfu on 18-3-14.
 *
 * @author: darrenfu
 * @date: 18 -3-14
 */

@ToString
public abstract class AbstractRule {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Getter
    private volatile boolean inited = false;

    /**
     * rule对应的service
     */
    @Getter
    @Setter
    private String service;

    @Getter
    @Setter
    private Boolean stripPrefix;

    @Getter
    private String prefix;

    /**
     * rule可匹配的请求path
     */
    @Getter
    @Setter
    private String path;

    @Getter
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


    public AbstractRule(String service, String path) {
        this(service, false, path);
    }


    public AbstractRule(String service, Boolean stripPrefix, String path) {
        this(service, stripPrefix, path, null, null);
    }

    public AbstractRule(String service, Boolean stripPrefix, String path,
                        String headerName, String headerValue) {
        this.service = service;
        this.stripPrefix = stripPrefix;
        this.path = path;
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    protected AntPathMatcher antPathMatcher() {
        return antPathMatcher;
    }

    /**
     * 路由规则类型 H
     *
     * @return rule type enum
     * @see RuleTypeEnum
     */
    public abstract RuleTypeEnum ruleType();


    public synchronized void init() {
        if (inited) {
            return;
        }
        if (StringUtils.isNotEmpty(path)) {
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            int secondSlash = path.indexOf("/", 1);

            if (secondSlash > 0) {
                prefix = path.substring(0, secondSlash);
            }
        }

        if (this.isAntPath == null) {
            this.isAntPath = antPathMatcher().isPattern(path);
        }
        this.inited = true;
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
