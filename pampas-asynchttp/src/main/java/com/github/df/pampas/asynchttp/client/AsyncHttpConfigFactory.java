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

package com.github.df.pampas.asynchttp.client;

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

    private static HashedWheelTimer timer = new HashedWheelTimer();

    /**
     * 生成默认的httpclient config
     *
     * @return the config
     */
    public static AsyncHttpClientConfig createConfig(int connectTimeout, int requestTimeout) {
        timer.start();
        DefaultChannelPool channelPool = new DefaultChannelPool(60000,
                -1,
                DefaultChannelPool.PoolLeaseStrategy.LIFO,
                timer,
                300000);// cleanerPeriod 不能超过MaxIdleTime
        //ahc-default.properties
        return new DefaultAsyncHttpClientConfig.Builder()
//                .setFollowRedirect(false)
//                .setIoThreadsCount(ioThreads);
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
                .setMaxConnections(10_000)
                .setMaxConnectionsPerHost(1_000)
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
