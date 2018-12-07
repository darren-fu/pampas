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

package com.github.pampas.common.config;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;

import java.math.BigDecimal;

/**
 * 版本化配置
 * Created by darrenfu on 18-3-4.
 *
 * @author: darrenfu
 * @date: 18 -3-4
 */
@Spi(scope = Scope.SINGLETON)
public interface VersionConfig {

    /**
     * 版本
     *
     * @return the version
     */
    BigDecimal configVersionNumber();


    /**
     * 与otherVersionConfig进行版本比较
     *
     * @param otherVersionConfig
     * @return 1版本比otherVersionConfig更高 0相同 -1低于otherVersionConfig
     */
    default int compareWith(VersionConfig otherVersionConfig) {
        if (otherVersionConfig == null) {
            return 1;
        }
        if (this.configVersionNumber() == null) {
            return 0;
        }
        if (otherVersionConfig.configVersionNumber() == null) {
            return 1;
        }
        return this.configVersionNumber().compareTo(otherVersionConfig.configVersionNumber());
    }


    default VersionConfig setupWithDefault(){
        return null;
    }


}
