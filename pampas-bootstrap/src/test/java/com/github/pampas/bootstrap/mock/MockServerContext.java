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

package com.github.pampas.bootstrap.mock;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.discover.ServerContext;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.discover.ServiceAndInstancesConfig;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.tools.CollectionTools;
import com.github.pampas.common.tools.CommonTools;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by darrenfu on 18-2-6.
 *
 * @author: darrenfu
 * @date: 18-2-6
 */
@SpiMeta(name = "mock-server-context", key = PampasConsts.ConfigLoaderKey.SERVER_CONTEXT)
public class MockServerContext implements ServerContext, Configurable<ServiceAndInstancesConfig> {

    private static final Logger log = LoggerFactory.getLogger(MockServerContext.class);

    private volatile ConcurrentHashMap<String, List<ServerInstance>> instanceMap;

    private ServiceAndInstancesConfig serviceAndInstancesConfig;

    public MockServerContext() {
        this.instanceMap = new ConcurrentHashMap<>();
    }

    public MockServerContext(String serviceName, String url) {
        this.instanceMap = new ConcurrentHashMap<>();
        this.instanceMap.put(serviceName, CollectionTools.toList(ServerInstance.buildWithUri(serviceName, url)));
    }


    @Override
    public long lastRefreshedTime() {
        return 0L;
    }

    @Override
    public List<String> getAllServiceName() {
        return Collections.list(instanceMap.keys());
    }

    @Override
    public List<ServerInstance> getServerList(String serviceName) {
//        this.instanceMap.put(serviceName, CollectionTools.toList(
//                ServerInstance.buildWithUri(serviceName, "http://localhost:9001")
//        ));
        return instanceMap.get(serviceName);
    }

    @Override
    public void refreshServerList() {
        ///do nothing
    }

    @Override
    public void refreshServerList(String serviceName) {
        ///do nothing
    }

    @Override
    public void clear() {
        instanceMap.clear();
    }


    @Override
    public List<ServerInstance> addServerList(List<ServerInstance> instanceList) {
        return null;
    }


    @Override
    public ServiceAndInstancesConfig getConfig() {
        return this.serviceAndInstancesConfig;
    }

    @Override
    public Class<ServiceAndInstancesConfig> configClass() {
        return ServiceAndInstancesConfig.class;
    }

    @Override
    public Configurable setupWithConfig(ServiceAndInstancesConfig... serviceAndInstanceConfigs) {
        this.serviceAndInstancesConfig = serviceAndInstanceConfigs[0];
        ConcurrentHashMap<String, List<ServerInstance>> map = new ConcurrentHashMap<>();
        for (ServiceAndInstancesConfig serviceAndInstance : serviceAndInstanceConfigs) {
            for (String serviceName : serviceAndInstance.getServices()) {
                if (map.containsKey(serviceName) && CollectionUtils.isNotEmpty(map.get(serviceName))) {
                    map.get(serviceName).removeAll(serviceAndInstance.getInstances(serviceName));
                    map.get(serviceName).addAll(serviceAndInstance.getInstances(serviceName));
                } else {
                    List<ServerInstance> list = new ArrayList<>(serviceAndInstance.getInstances(serviceName));
                    map.put(serviceName, list);
                }
            }
        }
        if (CommonTools.isNotEmpty(map)) {
            for (Map.Entry<String, List<ServerInstance>> entry : map.entrySet()) {
                log.info("{}刷新服务实例集合，<{}>拥有<{}>个实例：{}", getClass().getSimpleName(), entry.getKey(), entry.getValue().size(), entry.getValue());
            }
            this.instanceMap = map;
        }
        return this;
    }

    public ConcurrentHashMap deepCloneInstanceMap() {
        try {
            // 将对象写到流里
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(this.instanceMap);
            // 从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (ConcurrentHashMap) oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log.error("序列化失败服务实例集合：{}", e.getMessage(), e);
        }
        return new ConcurrentHashMap();
    }
}
