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
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * HTTP路由规则
 * Created by darrenfu on 18-3-14.
 *
 * @author: darrenfu
 * @date: 18-3-14
 */
@Data
public class HttpRule extends AbstractRule {

    private String mappedPath;

    @Override
    public RuleTypeEnum ruleType() {
        return RuleTypeEnum.HTTP;
    }

    @Override
    protected boolean checkMatch(PampasRequest<FullHttpRequest> request) {
        if (StringUtils.isNotEmpty(this.getPath())) {
            if (!super.getIsAntPath()) {
                return request.path().equals(this.getPath());
            }
            return antPathMatcher().match(this.getPath(), request.path());
        }

        if (StringUtils.isNotEmpty(this.getHeaderName())) {
            String headerValue = request.requestData().headers().get(this.getHeaderName());
            return antPathMatcher().match(this.getHeaderValue(), headerValue);
        }

        return false;
    }
}
