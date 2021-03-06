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

import com.github.pampas.common.config.ConfigLoader;
import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.tools.AssertTools;
import com.github.pampas.common.tools.InetTools;
import com.github.pampas.core.base.CoreVersion;
import com.github.pampas.core.server.listener.ServerConfigLoadedListener;
import com.github.pampas.core.server.listener.ServerReadyToStartListener;
import com.github.pampas.core.server.listener.ServerStartedListener;
import com.google.common.base.MoreObjects;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 服务器抽象类
 *
 * @author: darrenfu
 * @date: 18-1-22
 */
public abstract class AbstractServer implements PampasServer {
    private static final Logger log = LoggerFactory.getLogger(AbstractServer.class);

    private String group;
    private String serverName;

    protected int port;

    private String id;

    private String version;

    private InetAddress address;

    private long startTimestamp;

    protected ServerState serverState;

    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private ServerBootstrap bootstrap;
    private Channel channel;
    protected ServerConfig config;


    protected final AtomicReference<ServerState> serverStateRef;
    private Throwable throwable;

    private boolean useEpoll() {
        String sysName = System.getProperty("os.name");
        return sysName.contains("linux");
    }

    public AbstractServer(String groupName, String serverName, int port, ServerConfig config) {
        this(null, groupName, serverName, port, config);
    }

    public AbstractServer(String id, String groupName, String serverName, int port, ServerConfig config) {
        this.group = groupName;
        InetTools inetTools = new InetTools();
        InetAddress firstNonLoopbackAddress = inetTools.findFirstNonLoopbackAddress();
        inetTools.close();
//        this.address = getLocalHostLANAddress();
        this.address = firstNonLoopbackAddress;
        this.id = id == null ? group + "@" + address.getHostName() + ":" + port : id;
        this.startTimestamp = System.currentTimeMillis();
        this.version = CoreVersion.getVersion();
        this.serverName = serverName;
        this.port = port;
        this.config = config;
        serverStateRef = new AtomicReference<>(ServerState.Created);

        int bossThreads = ObjectUtils.defaultIfNull(config.getBoss(), 1);

        int defaultWorks = Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));

        int workThreads = ObjectUtils.defaultIfNull(config.getWorker(), defaultWorks);

        boss = useEpoll() ? new EpollEventLoopGroup(bossThreads) : new NioEventLoopGroup(bossThreads);
        worker = useEpoll() ? new EpollEventLoopGroup(workThreads) : new NioEventLoopGroup(workThreads);
        bootstrap = new ServerBootstrap();

        bootstrap.group(boss, worker).channel(useEpoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
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
            log.error("服务器状态错误,ID:{},端口:{}, 状态:{}", id, port, serverStateRef.get());
            throw new IllegalStateException("ServerStartedListener already started");
        }
        log.info("准备启动网关服务器,serverName:{},端口:{},版本:{}", serverName, port, CoreVersion.getVersion());
        // 调用start监听
        SpiContext<ServerReadyToStartListener> readyToStartListenerSpiContext = SpiContext.getContext(ServerReadyToStartListener.class);
        for (ServerReadyToStartListener readyToStartListener : readyToStartListenerSpiContext.getSpiInstances()) {
            readyToStartListener.ready(this);
        }

        // 启动时优先启动ConfigLoader， 加载所有Config
        List<ConfigLoader> configLoaderList = SpiContext.getContext(ConfigLoader.class).getSpiInstances();
        log.info("待执行的ConfigLoader:{}", configLoaderList);
        List<VersionConfig> versionConfigList = new ArrayList<>(configLoaderList.size());
        for (ConfigLoader configLoader : configLoaderList) {
            if (!configLoader.lazy()) {
                VersionConfig versionConfig = configLoader.loadConfig();
                versionConfigList.add(versionConfig);
                log.info("ConfigLoader加载完成:{}", configLoader.getClass().getSimpleName());
            }
        }
        //校验状态
        checkState(ServerState.Starting);
        // 调用start监听
        SpiContext<ServerConfigLoadedListener> serverConfigLoadedListener = SpiContext.getContext(ServerConfigLoadedListener.class);
        for (ServerConfigLoadedListener configLoadedListener : serverConfigLoadedListener.getSpiInstances()) {
            configLoadedListener.configLoaded(this, versionConfigList);
        }
        //校验状态
        checkState(ServerState.Starting);

        log.info("启动中:{}", this);
        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        log.info("启动成功,serverName:{},端口:{}, soBacklog:{}, soKeepLive:{}, tcpNodDelay:{}", serverName, port,
                config.soBacklog, config.soKeepAlive, config.tcpNoDelay);
        serverStateRef.set(ServerState.Running); // It will come here only if this was the thread that transitioned to Starting
        SpiContext<ServerStartedListener> startedListenerSpiContext = SpiContext.getContext(ServerStartedListener.class);
        for (ServerStartedListener serverStartedListener : startedListenerSpiContext.getSpiInstances()) {
            serverStartedListener.started(this);
        }
        //校验状态
        checkState(ServerState.Running);

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
        if (serverStateRef.compareAndSet(ServerState.Running, ServerState.Shutdown)) {
            log.info("服务准备停机,serverName:{},端口:{}", serverName, port);

            if (boss != null && !boss.isShuttingDown() && !boss.isShutdown()) {
                boss.shutdownGracefully();
            }
            if (worker != null && !worker.isShuttingDown() && !worker.isShutdown()) {
                worker.shutdownGracefully();
            }
            log.info("服务正常停机,serverName:{},端口:{}", serverName, port);
        } else {
            log.info("服务器尚未启动serverName:{},端口:{}", serverName, port);
        }
    }

    public void shutdownForcibly(Throwable throwable) {
        AssertTools.notNull(throwable, "强行终止服务必须提供异常");
        serverStateRef.getAndSet(ServerState.Shutdown);
        this.throwable = throwable;
    }

    private void checkState(ServerState state) {
        if (serverStateRef.get() == state) {
            return;
        }
        if (null != this.throwable) {
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            this.throwable.printStackTrace(printWriter);
            log.error("可能导致终止的原因:{}", result.toString());
            this.throwable = null;
        }
        throw new IllegalStateException("网关服务状态错误:" + serverStateRef.get() + "，应该是:" + state);
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
                throw new IllegalStateException("ServerStartedListener not started yet.");
            case Running:
                channel.closeFuture().await();
                break;
            case Shutdown:
                // Nothing to do as it is already shutdown.
                break;
        }
    }


    public InetAddress getLocalHostLANAddress() {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String version() {
        return version;
    }

    @Override
    public String group() {
        return group;
    }

    @Override
    public String serverName() {
        return serverName;
    }

    @Override
    public InetAddress address() {
        return address;
    }

    @Override
    public Integer port() {
        return port;
    }

    @Override
    public Long startTimestamp() {
        return this.startTimestamp;
    }

    protected void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    @Override
    public ServerState status() {
        return serverStateRef.get();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("serverName", serverName)
                .add("version", version)
                .add("port", port)
                .add("id", id)
                .add("serverState", serverState)
                .toString();
    }
}
