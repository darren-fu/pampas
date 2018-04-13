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

package com.github.pampas.discover.simple;

import com.github.pampas.common.discover.ServerContext;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.tools.CollectionTools;
import com.github.pampas.common.tools.StreamTools;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by darrenfu on 18-2-6.
 *
 * @author: darrenfu
 * @date: 18-2-6
 */
@SpiMeta(name = "simple")
public class SimpleServerContext implements ServerContext {

    private ConcurrentHashMap<String, List<ServerInstance>> instanceMap;

    public SimpleServerContext() {
        this.instanceMap = new ConcurrentHashMap<>();
    }

    public SimpleServerContext(String serviceName, String url) {
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

        Map<String, List<ServerInstance>> listMap = StreamTools.groupBy(instanceList, ServerInstance::getServiceName);
        for (Map.Entry<String, List<ServerInstance>> entry : listMap.entrySet()) {
            if (instanceMap.contains(entry.getKey())) {
                instanceMap.get(entry.getKey()).removeAll(entry.getValue());
                instanceMap.get(entry.getKey()).addAll(entry.getValue());
            } else {
                instanceMap.put(entry.getKey(), entry.getValue());
            }
        }
        instanceMap.putAll(StreamTools.groupBy(instanceList, ServerInstance::getServiceName));
        LinkedList list = new LinkedList();
        instanceMap.values().forEach(v -> list.addAll(v));
        return list;
    }

}
