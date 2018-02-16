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

package com.github.df.pampas.grpc;

import com.github.df.pampas.common.exec.Caller;
import com.github.df.pampas.common.exec.payload.RequestInfo;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.concurrent.CompletableFuture;

/**
 * Created by darrenfu on 18-2-15.
 *
 * @author: darrenfu
 * @date: 18-2-15
 */
public class GrpcCaller implements Caller<FullHttpRequest,Object> {
    @Override
    public Object call(RequestInfo<FullHttpRequest> req) {
        return null;
    }

    @Override
    public CompletableFuture<Object> asyncCall(RequestInfo<FullHttpRequest> req) {
        return null;
    }
}
