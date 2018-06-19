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

package com.github.pampas.asynchttp.client;

import io.netty.util.HashedWheelTimer;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.channel.DefaultKeepAliveStrategy;
import org.asynchttpclient.netty.channel.DefaultChannelPool;

import static org.asynchttpclient.config.AsyncHttpClientConfigDefaults.*;

/**
 * HttpClient config 工厂类
 * Created by darrenfu on 17-6-19.
 */
public class AsyncHttpConfigFactory {

    public static final AsyncHttpClientConfig DEFAULT_CONFIG = AsyncHttpConfigFactory.createConfig(3000, 30_000);

    /**
     * 生成默认的httpclient config
     *
     * @return the config
     */
    public static AsyncHttpClientConfig createConfig(int connectTimeout, int requestTimeout) {
        HashedWheelTimer timer = new HashedWheelTimer();
        timer.start();
        // cleanerPeriod 不能超过MaxIdleTime
        DefaultChannelPool channelPool = new DefaultChannelPool(60_000,
                -1,
                DefaultChannelPool.PoolLeaseStrategy.LIFO,
                timer,
                30_000);
        //ahc-default.properties
        String sysName = System.getProperty("os.name");
        return new DefaultAsyncHttpClientConfig.Builder()
//                .setFollowRedirect(false)
//                .setIoThreadsCount(ioThreads);
                .setUseNativeTransport(sysName.contains("linux"))
                .setSoReuseAddress(true)
                .setConnectTimeout(connectTimeout) //连接超时
                .setRequestTimeout(requestTimeout) //请求请求超时
                .setReadTimeout(defaultReadTimeout()) //60_000
                .setHandshakeTimeout(defaultHandshakeTimeout()) //10_000
                .setUserAgent(defaultUserAgent())//AHC/2.0
                .setConnectionTtl(defaultConnectionTtl()) // -1 -> 300_000
                .setPooledConnectionIdleTimeout(defaultPooledConnectionIdleTimeout())//60000
                .setValidateResponseHeaders(defaultValidateResponseHeaders())//true
                .setCompressionEnforced(defaultCompressionEnforced())//false
                .setKeepAlive(true)
                .setKeepAliveStrategy(new DefaultKeepAliveStrategy())
                .setMaxConnections(1_000_000)
                .setMaxConnectionsPerHost(100_000)
                .setMaxRequestRetry(0)
                .setChannelPool(channelPool)
                .build();
    }

    /**
     * Refresh config async http client config.
     *
     * @return the async http client config
     */
    public static AsyncHttpClientConfig refreshConfig() {
        return null;
    }


}
