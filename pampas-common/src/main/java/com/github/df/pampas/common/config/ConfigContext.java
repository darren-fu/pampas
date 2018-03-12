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

package com.github.df.pampas.common.config;

import com.github.df.pampas.common.tools.ClassTools;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置管理器
 * Created by darrenfu on 18-3-8.
 *
 * @author: darrenfu
 * @date: 18-3-8
 */
public class ConfigContext {


    private static ConcurrentHashMap<Class<? extends VersionConfig>, Class<?>> configRelMap = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Class<? extends VersionConfig>, VersionConfig> configInstanceMap = new ConcurrentHashMap<>();


    public static void addListenerConfig(Class<? extends VersionConfig> configClz, Class configurableClz) {
        configRelMap.put(configClz, configurableClz);
    }

    public static <T extends VersionConfig> T getConfig(Class<T> configClz) {
        VersionConfig config = configInstanceMap.get(configClz);

        if (config == null) {
            T instance = ClassTools.instance(configClz);
            instance.setupWithDefault();
            T loadConfig = loadConfig(configClz);

            if (loadConfig != null) {
                instance = loadConfig;
            }

            configInstanceMap.putIfAbsent(configClz, instance);
            config = configInstanceMap.get(configClz);
        }
        return (T) config;
    }


    public static <T extends VersionConfig> T loadConfig(Class<T> configClz) {

        ///todo : load config from remote server

        return null;
    }

}
