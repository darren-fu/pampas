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

package com.github.df.pampas.okhttp;

import com.github.df.pampas.common.discover.ServerInstance;
import com.github.df.pampas.common.exec.Caller;
import io.netty.handler.codec.http.FullHttpRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.CompletableFuture;

/**
 * Created by darrenfu on 18-2-20.
 *
 * @author: darrenfu
 * @date: 18-2-20
 */
public class OkhttpCaller implements Caller<FullHttpRequest, Response> {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public Response call(FullHttpRequest req, ServerInstance serverInstance) {
        return null;
    }

    @Override
    public CompletableFuture<Response> asyncCall(FullHttpRequest req, ServerInstance serverInstance) {

        Request request = new Request.Builder()
//                .url(fetchUrl)
                .build();
//        request.newBuilder()
        return null;
    }
}
