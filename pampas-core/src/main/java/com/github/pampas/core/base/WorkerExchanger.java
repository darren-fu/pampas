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

import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.config.SystemConfig;
import com.github.pampas.common.exec.Worker;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.tools.CommonTools;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Worker 交换机
 * 维护Worker和Service之间的关系，帮助更快将指定服务的请求路由到对应的worker处理器上
 * Created by darrenfu on 18-3-13.
 *
 * @author: darrenfu
 * @date: 18-3-13
 */
public class WorkerExchanger implements Configurable<SystemConfig> {

    private static final Logger log = LoggerFactory.getLogger(WorkerExchanger.class);

    private static ConcurrentHashMap<String, String> serviceAndWorkerMap = new ConcurrentHashMap<>();

    private String defaultWorker;


    public static WorkerExchanger getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void mark(String serviceName, String worker) {
        serviceAndWorkerMap.putIfAbsent(serviceName, worker);
    }


    public String exchang(String serviceName) {
        String workerName = serviceAndWorkerMap.get(serviceName);
        if (StringUtils.isNotEmpty(workerName)) {
            return workerName;
        }
        if (StringUtils.isNotEmpty(defaultWorker)) {
            return defaultWorker;
        }

        SpiContext<Worker> workerSpiContext = SpiContext.getContext(Worker.class);
        List<Class<Worker>> workerClasses = workerSpiContext.getSpiClasses(null);
        if (workerClasses != null && workerClasses.size() > 0) {
            String spiName = SpiContext.getSpiName(workerClasses.get(0));
            return spiName;
        }

        return null;
    }


    private String getDefaultWorker() {
        return "";
    }

    @Override
    public SystemConfig getConfig() {
        return null;
    }

    @Override
    public Class<SystemConfig> configClass() {
        return SystemConfig.class;
    }

    @Override
    public Configurable setupWithConfig(SystemConfig systemConfig) {
        this.defaultWorker = systemConfig.getDefaultWorker();
        log.debug("defaultWorker:{}", systemConfig.getDefaultWorker());
        return this;
    }

    private static class InstanceHolder {
        private static WorkerExchanger INSTANCE = new WorkerExchanger();


    }


}
