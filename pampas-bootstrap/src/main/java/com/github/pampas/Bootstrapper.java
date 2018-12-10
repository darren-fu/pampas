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

package com.github.pampas;

import com.github.pampas.core.base.PampasContext;
import com.github.pampas.core.server.GatewayServer;
import com.github.pampas.core.server.ServerConfig;
import com.github.pampas.storage.SpringStorageApp;
import com.github.pampas.storage.base.ServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

/**
 * Created by darrenfu on 18-4-13.
 *
 * @author: darrenfu
 * @date: 18-4-13
 */
public class Bootstrapper {

    private static final Logger log = LoggerFactory.getLogger(Bootstrapper.class);

    public static void main(String[] args) {
        try {
            Long start = System.currentTimeMillis();
            SpringStorageApp.init(args, (ctx, serverProperties) -> {
                BeanCopier beanCopier = BeanCopier.create(ServerProperties.class, ServerConfig.class, false);
                ServerConfig serverConfig = new ServerConfig();
                beanCopier.copy(serverProperties, serverConfig, null);
                log.info("加载系统配置:{}", serverProperties);
                GatewayServer gatewayServer = new GatewayServer(serverProperties.getId(),
                        serverProperties.getGroup(),
                        serverProperties.getPort(),
                        serverConfig);
                PampasContext.setCurrentServer(gatewayServer);
                gatewayServer.setStartTimestamp(start);

                try {
                    gatewayServer.start();
                } catch (InterruptedException e) {
                    log.error("服务器中止:{}", e.getMessage());
                }
            });
        } catch (Throwable throwable) {
            log.error("启动发生错误:{}", throwable.getMessage(), throwable);
        }
    }
}
