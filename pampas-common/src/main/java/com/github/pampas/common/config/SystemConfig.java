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

import com.github.pampas.common.extension.SpiMeta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by darrenfu on 18-3-13.
 *
 * @author: darrenfu
 * @date: 18-3-13
 */
@SpiMeta(name = "config-system")
@Getter
@Setter
public class SystemConfig implements VersionConfig {

    private String defaultWorker;


    @Override
    public BigDecimal configVersionNumber() {
        return null;
    }

    @Override
    public VersionConfig setupWithDefault() {
        return null;
    }
}
