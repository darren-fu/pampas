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

package com.github.pampas.bootstrap.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网关服务启动类
 *
 * @author: darrenfu
 * @date: 18-1-17
 */
public class TestServer {
    private static final Logger log = LoggerFactory.getLogger(TestServer.class);

    private String serverName;
    private volatile Boolean start;

    private Integer port;

    private ServerBootstrap boot;
    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workGroup;

    public TestServer(String serverName, final Integer port) {
        this.start = false;
        this.serverName = serverName;
        this.port = port;
        this.boot = new ServerBootstrap();
        this.bossGroup = new NioEventLoopGroup(1);
        this.workGroup = new NioEventLoopGroup();
    }


    private ServerBootstrap serverBootstrap() {
        boot.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                                  @Override
                                  protected void initChannel(SocketChannel ch) throws Exception {
                                      ch.pipeline()
                                              .addLast("decoder", new HttpRequestDecoder())
                                              .addLast("http-aggregator", new HttpObjectAggregator(65536))
//                                              .addLast("encoder", new HttpResponseDecoder())
                                              .addLast("base-encoder", new HttpResponseEncoder())
                                              .addLast("chunk", new ChunkedWriteHandler())
                                              .addLast("handler", new TestHttpServerHandler());

                                  }
                              }
                );


        return boot;
    }

    /**
     * 启动
     */
    public synchronized void start() {
        if (start) {
            log.info("Test服务器已经启动,serverName:{},端口:{}", port);
            return;
        }

        try {
            log.info("准备启动测试服务器,serverName:{},端口:{}", serverName, port);
            final ServerBootstrap boot = this.serverBootstrap();
            // 服务器绑定端口监听
            ChannelFuture f = boot.bind(port).sync();
            log.info("启动成功,serverName:{},端口:{}", serverName, port);
            // 监听服务器关闭监听
            f.channel().closeFuture().addListener(future -> {
                start = false;
                log.info("网关服务优雅停机:{}@{}", serverName, port);
            });

            Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown()));
            this.start = true;
        } catch (Exception e) {
            log.warn("测试服务器出现错误:{}@{}", serverName, port, e);
            this.start = false;
        }

    }

    /**
     * 优雅停机
     */
    public void shutdown() {
        if (start) {
            if (!bossGroup.isShuttingDown() && !bossGroup.isShutdown()) {
                bossGroup.shutdownGracefully();
            }
            if (!workGroup.isShuttingDown() && !workGroup.isShutdown()) {
                workGroup.shutdownGracefully();
            }
        } else {
            log.info("服务器尚未启动:{}@{}", serverName, port);
        }

    }
}
