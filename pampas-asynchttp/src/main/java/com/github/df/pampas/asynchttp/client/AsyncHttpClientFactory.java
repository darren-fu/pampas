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

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClient;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by darrenfu on 18-2-20.
 *
 * @author: darrenfu
 * @date: 18-2-20
 */
public class AsyncHttpClientFactory {

    private static ConcurrentHashMap<String, AsyncHttpClient> httpClientMap = new ConcurrentHashMap<>();

    public static AsyncHttpClient getAsyncHttpClient(String serviceName) {
        return getAsyncHttpClient(serviceName, 3_000, 30_000);
    }


    public static AsyncHttpClient getAsyncHttpClient(String serviceName, int connectTimeout, int requestTimeout) {
        if (httpClientMap.contains(serviceName)) {
            return httpClientMap.get(serviceName);
        } else {
            AsyncHttpClientConfig config = AsyncHttpConfigFactory.createConfig(connectTimeout, requestTimeout);
            DefaultAsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(config);
            httpClientMap.putIfAbsent(serviceName, asyncHttpClient);
            return httpClientMap.get(serviceName);
        }
    }

    public static AsyncHttpClient refreshAsyncHttpClient(String serviceName, int connectTimeout, int requestTimeout) {
        // todo:refresh with new config
        return null;
    }

}
