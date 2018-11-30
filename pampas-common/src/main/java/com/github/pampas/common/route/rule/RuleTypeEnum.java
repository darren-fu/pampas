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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 匹配规则分类枚举
 * Created by darrenfu on 18-4-28.
 *
 * @author: darrenfu
 * @date: 18-4-28
 */
public enum RuleTypeEnum {

    HTTP("http"), DUBBO("dubbo"), GRPC("grpc");

    private String value;

    RuleTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RuleTypeEnum getEnum(String value){
        for (RuleTypeEnum anEnum : values()) {
            if(anEnum.getValue().equals(value)){
                return anEnum;
            }
        }
        throw new IllegalArgumentException("不合法的数据:" + value);
    }

}
