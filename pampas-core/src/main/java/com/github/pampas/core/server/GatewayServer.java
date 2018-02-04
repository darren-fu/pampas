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

package com.github.pampas.core.server;

import com.github.pampas.core.handler.FinalExceptionHandler;
import com.github.pampas.core.handler.HeartbeatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.RejectedExecutionHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网关服务启动类
 *
 * @author: darrenfu
 * @date: 18-1-17
 */
public class GatewayServer extends AbstractServer {
    private static final Logger log = LoggerFactory.getLogger(GatewayServer.class);

    public GatewayServer(String serverName, final Integer port) {
        super(serverName, port, ServerConfig.defaultConfig());
    }

    static LoggingHandler loggingHandler = new LoggingHandler(LogLevel.DEBUG);

    static FinalExceptionHandler exceptionHandler = new FinalExceptionHandler();

    static final EventExecutorGroup businessExecutors = new DefaultEventExecutorGroup(20,
            new DefaultThreadFactory("http-group", false, Thread.NORM_PRIORITY),
            100,
            RejectedExecutionHandlers.reject());


    @Override

    public ChannelInitializer<SocketChannel> newChannelInitializer() {

        return new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
//                        .addLast(loggingHandler)
                        .addLast(new IdleStateHandler(30, 0, 10))
                        .addLast("decoder", new HttpRequestDecoder())
                        .addLast("http-aggregator", new HttpObjectAggregator(65536))
                        .addLast("encoder", new HttpResponseEncoder())
                        .addLast("chunk", new ChunkedWriteHandler())
                        .addLast(businessExecutors, "business-handler", new HttpServerHandler())
                        .addLast(new HeartbeatHandler())
                        .addLast(exceptionHandler);

            }
        };
    }

}
