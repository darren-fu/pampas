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

package com.github.df.pampas.common.discover;

import com.github.df.pampas.common.extension.Spi;

import java.util.List;

/**
 * 服务实例上下文
 * Created by darrenfu on 17-6-26.
 */
@Spi
public interface ServerContext {

    /**
     * 获取所有service name
     *
     * @return the all service name
     */
    List<String> getAllServiceName();


    /**
     * 获取指定serviceName下的服务实例列表
     *
     * @param serviceName the service name
     * @return the server list
     */
    List<ServerInstance> getServerList(String serviceName);

    /**
     * 刷新所有服务实例
     */
    void refreshServerList();

    /**
     * 刷新serviceName下的服务实例
     *
     * @param serviceName the service name
     */
    void refreshServerList(String serviceName);


    /**
     * clear
     */
    void clear();

    /**
     * Add server list list.
     *
     * @param instanceList the instance list
     * @return the list
     */
    List<ServerInstance> addServerList(List<ServerInstance> instanceList);

}
