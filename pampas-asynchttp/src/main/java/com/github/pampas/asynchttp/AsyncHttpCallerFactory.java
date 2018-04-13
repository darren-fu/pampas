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

package com.github.pampas.asynchttp;

import com.github.pampas.asynchttp.client.AsyncHttpClientFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by darrenfu on 18-2-20.
 *
 * @author: darrenfu
 * @date: 18-2-20
 */
public class AsyncHttpCallerFactory {

    private static ConcurrentHashMap<String, AsyncHttpCaller> httpCallerMap = new ConcurrentHashMap<>();


    public static AsyncHttpCaller getHttpCaller(String serviceName) {

        if (httpCallerMap.contains(serviceName)) {
            return httpCallerMap.get(serviceName);
        }
        httpCallerMap.putIfAbsent(serviceName, new AsyncHttpCaller(serviceName, AsyncHttpClientFactory.getAsyncHttpClient(serviceName)));
        return httpCallerMap.get(serviceName);
    }

}
