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

/**
 * Created by darrenfu on 18-3-14.
 *
 * @author: darrenfu
 * @date: 18-3-14
 */
@Data
public class GrpcRule extends AbstractRule {

    public GrpcRule(String service, Boolean stripPrefix, String path) {
        super(service, stripPrefix, path);
    }

    @Override
    public RuleTypeEnum ruleType() {
        return RuleTypeEnum.GRPC;
    }

    @Override
    protected boolean checkMatch(PampasRequest<FullHttpRequest> request) {
        return false;
    }
}
