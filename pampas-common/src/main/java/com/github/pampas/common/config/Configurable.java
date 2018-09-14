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

import com.github.pampas.common.extension.SpiContext;

/**
 * 可配置化的定义接口
 * Created by darrenfu on 18-3-4.
 *
 * @author: darrenfu
 * @date: 18 -3-4
 */
public interface Configurable<T extends VersionConfig> {


    /**
     * 获取KEY，用于区分不同的可配置对象
     *
     * @return
     */
    default String getKey() {
        return SpiContext.getSpiName(getClass());
    }


    /**
     * 获取配置项
     *
     * @return the version config
     */
    T getConfig();


    Class<T> configClass();


    /**
     * 使用配置项配置当前对象
     */
    Configurable setupWithConfig(T... t);


    default void register() {
    }


}
