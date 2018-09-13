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
import com.github.pampas.common.discover.ServerContext;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.tools.CollectionTools;
import com.github.pampas.core.base.AbstractConfigLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by darrenfu on 18-2-6.
 *
 * @author: darrenfu
 * @date: 18-2-6
 */
@SpiMeta(name = "mock-server-context", key = PampasConsts.ConfigLoaderKey.SERVER_CONTEXT)
public class MockServerContext extends AbstractConfigLoader<ServiceAndInstances> implements ServerContext {

    private volatile ConcurrentHashMap<String, List<ServerInstance>> instanceMap;

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
        this.instanceMap.put(serviceName, CollectionTools.toList(
//                ServerInstance.buildWithUri(serviceName, "http://localhost:9003"),
//                ServerInstance.buildWithUri(serviceName, "http://localhost:9002"),
                ServerInstance.buildWithUri(serviceName, "http://localhost:9001")
        ));

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
    public ServiceAndInstances doConfigLoad() {
        log.info("加载ServerContext的配置:{}", getClass().getSimpleName());
        ServiceAndInstances serviceAndInstances = new ServiceAndInstances();
        serviceAndInstances.addServiceAndInstance("TestService",
                Arrays.asList(
                        ServerInstance.buildWithUri("TestService", "http://localhost:7001")
                ));

        ConcurrentHashMap<String, List<ServerInstance>> map = new ConcurrentHashMap<>();
        map.putAll(serviceAndInstances.getServiceMap());
        this.instanceMap = map;

        return serviceAndInstances;
    }

    @Override
    public Class<ServiceAndInstances> configClass() {
        return ServiceAndInstances.class;
    }
}
