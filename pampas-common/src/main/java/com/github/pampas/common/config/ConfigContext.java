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

import com.github.pampas.common.tools.ClassTools;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置管理器
 * Created by darrenfu on 18-3-8.
 *
 * @author: darrenfu
 * @date: 18 -3-8
 */
public class ConfigContext {

    private static final Byte ONE = Byte.valueOf("1");

    private static ConcurrentHashMap<Class<? extends VersionConfig>, WeakHashMap<Configurable, Byte>> configAndConfigurableMap = new ConcurrentHashMap<>();

    //VersionConfig 实例缓存    VersionConfig都保持单例
    private static ConcurrentHashMap<Class<? extends VersionConfig>, VersionConfig> configInstanceMap = new ConcurrentHashMap<>();


    /**
     * 缓存VersionConfig和Configurable的关系
     *
     * @param configClz    the config clz
     * @param configurable the configurable
     */
    public static void markConfigurable(Class<? extends VersionConfig> configClz, Configurable configurable) {

        if (configAndConfigurableMap.contains(configClz)) {
            WeakHashMap<Configurable, Byte> instanceMap = configAndConfigurableMap.get(configClz);
            instanceMap.put(configurable, ONE);
        } else {
            WeakHashMap<Configurable, Byte> instanceMap = new WeakHashMap<>();
            instanceMap.put(configurable, ONE);
            configAndConfigurableMap.putIfAbsent(configClz, instanceMap);
        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Map<String, WeakHashMap<Object, Byte>> map = new HashMap();

        WeakHashMap<Object, Byte> weakHashMap = new WeakHashMap<>();
        ConfigContext c1 = new ConfigContext();
        ConfigContext c2 = new ConfigContext();

        weakHashMap.put(c1, Byte.MIN_VALUE);
        weakHashMap.put(c2, Byte.MIN_VALUE);
        System.out.println("weakHashMap size:" + weakHashMap.size());

        map.put("txt", weakHashMap);
        c2 = null;
        System.gc();

        for (Map.Entry<String, WeakHashMap<Object, Byte>> entry : map.entrySet()) {
            WeakHashMap<Object, Byte> value = entry.getValue();

            System.out.println("weakHashMap size:" + value.size());


        }


    }


    /**
     * Gets config.
     *
     * @param <T>       the type parameter
     * @param configClz the config clz
     * @return the config
     */
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


    /**
     * Load config t.
     *
     * @param <T>       the type parameter
     * @param configClz the config clz
     * @return the t
     */
    public static <T extends VersionConfig> T loadConfig(Class<T> configClz) {

        ///todo : load config from remote server

        return null;
    }

}
