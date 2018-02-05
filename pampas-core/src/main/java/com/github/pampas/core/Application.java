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

package com.github.pampas.core;

import com.github.df.pampas.common.tracer.OpenTracingContext;
import com.github.pampas.core.http2.GatewayServer2;
import com.github.pampas.core.server.GatewayServer;


//500 (Internal Server Error/内部服务器错误)
//501 (Not Implemented/未实现)
//502 (Bad Gateway/错误的网关)
//503 (Service Unavailable/服务无法获得)
//504 (Gateway Timeout/网关超时)
//505 (HTTP Version Not Supported/不支持的 HTTP 版本)

/**
 * 网关服务启动类
 *
 * @author: darrenfu
 * @date: 18-1-17
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {

//        OpenTracingContext.setTracerFactory(new ZipkinTraceFactory());
        GatewayServer server1 = new GatewayServer("server1", 9000);
        GatewayServer2 server2 = new GatewayServer2("server2", 9001);
        server1.start();
//        server2.start();

    }
}
