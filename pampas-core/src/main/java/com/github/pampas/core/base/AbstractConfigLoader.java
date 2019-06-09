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

package com.github.pampas.core.base;

import com.github.pampas.common.config.ConfigLoader;
import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.core.server.ServerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 配置管理器
 * Created by darrenfu on 18-3-8.
 *
 * @author: darrenfu
 * @date: 18 -3-8
 */
public abstract class AbstractConfigLoader<T extends VersionConfig> implements ConfigLoader<T> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private static final Byte ONE = Byte.valueOf("1");

    private static ConcurrentHashMap<Class<? extends VersionConfig>, WeakHashMap<Configurable, Byte>> configAndConfigurableMap = new ConcurrentHashMap<>();

    //VersionConfig 实例缓存    VersionConfig都保持单例
    private static ConcurrentHashMap<Class<? extends VersionConfig>, VersionConfig> configInstanceMap = new ConcurrentHashMap<>();

    private List<Configurable<T>> configurableList;

    private AtomicBoolean loaded = new AtomicBoolean(false);

    private volatile T config;

    private Object mutx = new Object();


    @Override
    public void addListener(Configurable<T> configurable) {
        if (configurableList == null) {
            configurableList = new ArrayList<>();
        }
        if (!configurableList.contains(configurable)) {
            configurableList.add(configurable);
        }
    }


    /**
     * Load config t.
     *
     * @return the t
     */
    @Override
    public T loadConfig() {

        if (loaded.get()) {
            return config;
        }
        T t = config;

        synchronized (mutx) {
            if (loaded.compareAndSet(false, true)) {
                Class<T> configClz = configClass();
                log.info("开始加载配置<{}>,加载器:{}", configClz.getSimpleName(), getClass().getSimpleName());
                try {
                    t = doConfigLoad();
                } catch (Exception ex) {
                    log.error("加载配置<{}>失败,ex:{}", configClz.getSimpleName(), ex.getMessage(), ex);
                    ServerState status = PampasContext.getCurrentServer().status();
                    if (status == ServerState.Created || status == ServerState.Starting) {
                        PampasContext.getCurrentServer().shutdownForcibly(ex);
                    }
                }
                if (t == null) {
                    log.warn("完成加载配置<{}>,结果:{}", configClz.getSimpleName(), t);
                    loaded.set(false);
                    return config;
                }
                config = t;
                log.info("完成加载配置<{}>,结果:{}", configClz.getSimpleName(), t);
                if (t != null && configurableList != null && configurableList.size() > 0) {
                    for (Configurable<T> configurable : configurableList) {
                        configurable.setupWithConfig(t);
                    }
                }
            }
        }
        return t;
    }

    public abstract T doConfigLoad();

    @Override
    public T refreshConfig() {
        this.loaded.compareAndSet(true, false);
        return this.loadConfig();
    }
}
