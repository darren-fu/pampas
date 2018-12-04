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

package com.github.pampas.common.route;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.discover.ServerInstance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 标明路由的目标服务名称 和 路径
 * Created by darrenfu on 18-2-23.
 *
 * @author: darrenfu
 * @date: 18-2-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locator {

    /**
     * 工作者
     *
     * @see com.github.pampas.common.base.PampasConsts.Worker
     */
    private String worker;

    /**
     * upstream的服务名称
     */
    private String serviceName;


    private String mappedPath;

    /**
     * 负载均衡器
     *
     * @see PampasConsts.LoadBalancer
     */
    private String loadBalancer;


    private List<ServerInstance> instanceList;
}
