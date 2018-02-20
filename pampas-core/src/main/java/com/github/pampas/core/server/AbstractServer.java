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

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 服务器抽象类
 *
 * @author: darrenfu
 * @date: 18-1-22
 */
public abstract class AbstractServer {
    private static final Logger log = LoggerFactory.getLogger(AbstractServer.class);

    private String serverName;

    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private ServerBootstrap bootstrap;
    private Channel channel;
    protected ServerConfig config;
    protected int port;


    protected final AtomicReference<ServerState> serverStateRef;

    public AbstractServer(String serverName, int port, ServerConfig config) {
        this.serverName = serverName;
        this.port = port;
        this.config = config;
        serverStateRef = new AtomicReference<>(ServerState.Created);
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();

        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                // 启用Pool Bytebuf,http request的content将使用堆外pooled direct bytebuf,其它信息(header)仍然是堆内
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .option(ChannelOption.SO_BACKLOG, config.soBacklog)
                .childOption(ChannelOption.SO_KEEPALIVE, config.soKeepAlive)
                .childOption(ChannelOption.TCP_NODELAY, config.tcpNoDelay)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_RCVBUF, 24 * 1024)
                .childOption(ChannelOption.SO_SNDBUF, 24 * 1024)
                //ChannelOut boundBuffer 高水位线 低水位线
                .option(ChannelOption.WRITE_BUFFER_WATER_MARK, WriteBufferWaterMark.DEFAULT)
                .handler(new LoggingHandler(LogLevel.DEBUG)).childHandler(newChannelInitializer());
    }

    public abstract ChannelInitializer<SocketChannel> newChannelInitializer();


    public synchronized ChannelFuture start() throws InterruptedException {
        if (!serverStateRef.compareAndSet(ServerState.Created, ServerState.Starting)) {
            log.error("服务器已经启动,serverName:{},端口:{}", port);
            throw new IllegalStateException("Server already started");
        }
        log.info("准备启动网关服务器,serverName:{},端口:{}", serverName, port);

        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        log.info("启动成功,serverName:{},端口:{}, soBacklog:{}, soKeepLive:{}, tcpNodDelay:{}", serverName, port,
                config.soBacklog, config.soKeepAlive, config.tcpNoDelay);

        serverStateRef.set(ServerState.Started); // It will come here only if this was the thread that transitioned to Starting
        channel = channelFuture.channel();
        channel.closeFuture().addListener(future -> {
            log.info("服务优雅停机,serverName:{},端口:{}", serverName, port);
        });
        // 监听服务器关闭监听

        Runtime.getRuntime().addShutdownHook(new Thread(() -> shutdown()));
        return channelFuture;
    }

    /**
     * 优雅停机
     */
    public void shutdown() {
        if (serverStateRef.compareAndSet(ServerState.Started, ServerState.Shutdown)) {
            if (boss != null && !boss.isShuttingDown() && !boss.isShutdown()) {
                boss.shutdownGracefully();
            }
            if (worker != null && !worker.isShuttingDown() && !worker.isShutdown()) {
                worker.shutdownGracefully();
            }
        } else {
            log.info("服务器尚未启动serverName:{},端口:{}", serverName, port);
        }
    }


    public void startAndWait() throws InterruptedException {
        start();
        try {
            waitTillShutdown();
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }


    public void waitTillShutdown() throws InterruptedException {
        ServerState serverState = serverStateRef.get();
        switch (serverState) {
            case Created:
            case Starting:
                throw new IllegalStateException("Server not started yet.");
            case Started:
                channel.closeFuture().await();
                break;
            case Shutdown:
                // Nothing to do as it is already shutdown.
                break;
        }
    }


    protected enum ServerState {Created, Starting, Started, Shutdown}

}
