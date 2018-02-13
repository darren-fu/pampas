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

package com.github.df.pampas.discover.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.catalog.model.CatalogService;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import com.github.df.pampas.common.discover.ServerContext;
import com.github.df.pampas.common.discover.ServerInstance;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by darrenfu on 18-2-7.
 *
 * @author: darrenfu
 * @date: 18-2-7
 */
public class ConsulServerContext implements ServerContext {
    /**
     * ConsulClient.getCatalogServices longPoll 只会侦测到Service以及Service实例 上线以及下线(register & deregister)
     * (health的变化不会影响getCatalogServices的结果)
     */

    private volatile ConcurrentHashMap<String, List<ServerInstance>> instancesMap = new ConcurrentHashMap<>();

    private ConsulClient consulClient;

    private Long lastUpdatedTimestamp = 0L;
    private long lastCatalogIndex = 0;
    private long lastHealthIndex = 0;

    private static final ScheduledExecutorService executors = Executors.newSingleThreadScheduledExecutor();

    public ConsulServerContext(String host, int port) {
        this.consulClient = new ConsulClient(host, port);
    }


    private void init() {
        long waitSeconds = 10;

        // 获取catalogServices 列表
        Response<Map<String, List<String>>> catalogServices = consulClient.getCatalogServices(new QueryParams(waitSeconds, lastCatalogIndex));

        if (lastCatalogIndex != catalogServices.getConsulIndex()) {
            for (Map.Entry<String, List<String>> entry : catalogServices.getValue().entrySet()) {
                instancesMap.putIfAbsent(entry.getKey(), Collections.emptyList());
            }
        }
        lastCatalogIndex = catalogServices.getConsulIndex();
        for (String serviceName : instancesMap.keySet()) {
            Response<List<CatalogService>> catalogService = consulClient.getCatalogService(serviceName, QueryParams.DEFAULT);

            for (CatalogService service : catalogService.getValue()) {

                System.out.println("serviceName:" + serviceName + "---service:" + service);
                System.out.println("service.getServicePort():" + service.getServicePort());
                System.out.println("service.getServiceAddress():" + service.getServiceAddress());
                System.out.println("service.getServiceName():" + service.getServiceName());
                System.out.println("service.getAddress():" + service.getAddress());

                for (String tag : service.getServiceTags()) {

                    System.out.println("tag:" + URLDecoder.decode(tag));
                }
                System.out.println("=========================================");
            }

        }


        for (String serviceName : instancesMap.keySet()) {
            Response<List<HealthService>> healthServices = consulClient.getHealthServices(serviceName, true, QueryParams.DEFAULT);
            for (HealthService healthService : healthServices.getValue()) {
                System.out.println("healthService:" + healthService.getService());
                String host = findHost(healthService);

                Map<String, String> metadata = getMetadata(healthService);
                Integer port = healthService.getService().getPort();
//                ServerInstance instance = ServerInstance.build(serviceName, )

            }
        }

    }

    public boolean isPassingChecks(HealthService service) {
        for (Check check : service.getChecks()) {
            if (check.getStatus() != Check.CheckStatus.PASSING) {
                return false;
            }
        }
        return true;
    }

    private static String findHost(HealthService healthService) {
        HealthService.Service service = healthService.getService();
        HealthService.Node node = healthService.getNode();

        if (StringUtils.isNotBlank(service.getAddress())) {
            return service.getAddress();
        } else if (StringUtils.isNotBlank(node.getAddress())) {
            return node.getAddress();
        }
        return node.getNode();
    }


    public static void main(String[] args) {
//        ConsulServerContext serverContext = new ConsulServerContext("192.168.1.248", 8500);
        ConsulServerContext serverContext = new ConsulServerContext("localhost", 8500);
        serverContext.init();
    }


    @Override
    public long lastRefreshedTime() {
        return this.lastUpdatedTimestamp;
    }

    @Override
    public List<String> getAllServiceName() {
        Response<Map<String, List<String>>> catalogServices = consulClient.getCatalogServices(QueryParams.DEFAULT);
        return null;
    }

    @Override
    public List<ServerInstance> getServerList(String serviceName) {
        return null;
    }

    @Override
    public void refreshServerList() {

    }

    @Override
    public void refreshServerList(String serviceName) {

    }

    @Override
    public void clear() {

    }

    @Override
    public List<ServerInstance> addServerList(List<ServerInstance> instanceList) {
        return null;
    }


    private static Map<String, String> getMetadata(HealthService healthService) {
        return getMetadata(healthService.getService().getTags());
    }

    private static Map<String, String> getMetadata(List<String> tags) {
        LinkedHashMap<String, String> metadata = new LinkedHashMap<>();
        if (tags != null) {
            for (String tag : tags) {
                String[] parts = StringUtils.split(tag, "=");
                switch (parts.length) {
                    case 0:
                        break;
                    case 1:
                        metadata.put(parts[0], parts[0]);
                        break;
                    case 2:
                        metadata.put(parts[0], parts[1]);
                        break;
                    default:
                        String[] end = Arrays.copyOfRange(parts, 1, parts.length);
                        metadata.put(parts[0], StringUtils.join(end, "="));
                        break;
                }

            }
        }

        return metadata;
    }

}
