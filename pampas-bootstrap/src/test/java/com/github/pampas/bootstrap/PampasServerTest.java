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

package com.github.pampas.bootstrap;

import com.github.pampas.bootstrap.test.TestServer;
import com.github.pampas.core.server.GatewayServer;
import org.junit.Test;

/**
 * Created by darrenfu on 18-3-14.
 *
 * @author: darrenfu
 * @date: 18-3-14
 */
public class PampasServerTest {


    @Test
    public void testStartPampasServer() throws InterruptedException {
        GatewayServer gatewayServer = new GatewayServer("gatewayServer", 9000);
        gatewayServer.start();
        Thread.currentThread().join();
    }

    @Test
    public void testStartTestServer() throws InterruptedException {
        TestServer testServer = new TestServer("test", 9001);
        testServer.start();
        Thread.currentThread().join();
    }

}
