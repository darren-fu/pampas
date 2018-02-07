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
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.health.model.Check;
import com.github.df.pampas.common.discover.ServerContext;
import com.github.df.pampas.common.discover.ServerInstance;
import com.github.df.pampas.common.tools.CommonTools;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by darrenfu on 18-2-7.
 *
 * @author: darrenfu
 * @date: 18-2-7
 */
public class ConsulServerContext implements ServerContext {

    private ConsulClient consulClient;


    public ConsulServerContext(String host, int port) {
        this.consulClient = new ConsulClient(host, port);
    }


    private void init() {
        URI uri = URI.create("192.169.20.131:8500");
        consulClient = new ConsulClient();
    }

    public static void main(String[] args) {
        URI uri = CommonTools.toURI("t://192.169.20.131:8500");
        System.out.println(uri.getScheme());
        System.out.println(uri.getPort());
        System.out.println(uri.getHost());

        ConsulClient consulClient = new ConsulClient("http://192.168.1.248", 8500);
        Response<Map<String, Service>> agentServices = consulClient.getAgentServices();
        System.out.println("agentServices:" + agentServices);

        Response<Map<String, List<String>>> catalogServices = consulClient.getCatalogServices(QueryParams.DEFAULT);
        catalogServices.getValue().keySet().forEach(System.out::println);


        Map<String, List<String>> listMap = catalogServices.getValue();
        for (Map.Entry<String, List<String>> entry : listMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        Response<List<Check>> healthChecksState = consulClient.getHealthChecksState(QueryParams.DEFAULT);
        for (Check check : healthChecksState.getValue()) {
            System.out.println("check:" + check.getStatus());
        }


        consulClient.getHealthChecksForService()





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
}
