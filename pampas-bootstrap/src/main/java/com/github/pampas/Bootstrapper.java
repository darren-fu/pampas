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

import com.github.pampas.core.server.GatewayServer;
import com.github.pampas.storage.SpringStorageApp;
import com.github.pampas.storage.base.SystemProps;

/**
 * Created by darrenfu on 18-4-13.
 *
 * @author: darrenfu
 * @date: 18-4-13
 */
public class Bootstrapper {
    public static void main(String[] args) {

        SpringStorageApp.init(args, (ctx) -> {
            System.out.println("SystemProps:" + SystemProps.getGatewayVersion());
            GatewayServer server1 = new GatewayServer("server1", 9000);
            try {
                server1.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
