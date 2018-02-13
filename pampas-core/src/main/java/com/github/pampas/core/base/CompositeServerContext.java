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

import com.github.df.pampas.common.discover.ServerContext;
import com.github.df.pampas.common.discover.ServerInstance;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 组合型 服务容器
 * Created by darrenfu on 18-2-11.
 *
 * @author: darrenfu
 * @date: 18-2-11
 */
public class CompositeServerContext implements ServerContext {

    private static final Logger log = LoggerFactory.getLogger(CompositeServerContext.class);

    private List<ServerContext> serverContextList;

    private ConcurrentHashMap<String, List<ServerInstance>> instanceMap;

    //ServerContext上次刷新时间k->v: obj.toString->lastRefreshTime
    private ConcurrentHashMap<String, Long> refreshMap;

    private Long lastRefreshedTime = 0L;

    private ScheduledExecutorService scheduledExecutor;

    private volatile AtomicBoolean merging = new AtomicBoolean(false);

    public CompositeServerContext(List<ServerContext> serverContextList) {
        this.serverContextList = serverContextList;
        this.instanceMap = new ConcurrentHashMap(32);
        this.refreshMap = new ConcurrentHashMap<>();
        this.scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        for (ServerContext context : this.serverContextList) {
            this.refreshMap.put(context.toString(), context.lastRefreshedTime());
        }
        // 定期检查内部ServerContext是否刷新
        scheduledExecutor.scheduleWithFixedDelay(() -> this.merge(), 1L, 3L, TimeUnit.SECONDS);

    }

    public void addServerContext(ServerContext serverContext) {
        if (serverContext != null) {
            this.serverContextList.add(serverContext);
        }
    }

    /**
     * 合并多个ServerContext的服务列表
     */
    private void merge() {
        try {
            if (merging.compareAndSet(false, true)) {
                for (ServerContext serverContext : serverContextList) {
                    if (serverContext.lastRefreshedTime() <= 0) {
                        //未刷新的ServerContext，强制刷新
                        serverContext.refreshServerList();
                    }

                    String contextHash = serverContext.toString();
                    Long lastRefreshTime = ObjectUtils.defaultIfNull(this.refreshMap.get(contextHash), 0L);
                    // 刷新时间不同，说明serverContext已经刷新
                    if (lastRefreshTime == null
                            || lastRefreshTime <= 0
                            || lastRefreshTime.longValue() != serverContext.lastRefreshedTime()) {
                        for (String serviceName : serverContext.getAllServiceName()) {
                            instanceMap.put(serviceName, serverContext.getServerList(serviceName));
                        }
                        this.refreshMap.put(contextHash, serverContext.lastRefreshedTime());
                        this.lastRefreshedTime = System.currentTimeMillis();
                    }
                }
            }
        } catch (Exception ex) {
            log.error("合并CompositeServerContext服务列表失败", ex);
        } finally {
            merging.set(false);
        }

    }


    @Override
    public long lastRefreshedTime() {
        return this.lastRefreshedTime;
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
        for (ServerContext serverContext : serverContextList) {
            serverContext.refreshServerList();
            this.merge();
        }
    }

    @Override
    public void refreshServerList(String serviceName) {
        for (ServerContext serverContext : serverContextList) {
            serverContext.refreshServerList(serviceName);
            this.merge();
        }
    }

    @Override
    public void clear() {
        this.instanceMap.clear();

    }

    @Override
    public List<ServerInstance> addServerList(List<ServerInstance> instanceList) {
        throw new UnsupportedOperationException(this.getClass() + "不支持手动添加服务列表");
    }
}
