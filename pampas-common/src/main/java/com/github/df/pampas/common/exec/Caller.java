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

package com.github.df.pampas.common.exec;

import com.github.df.pampas.common.exec.payload.RequestInfo;
import com.github.df.pampas.common.exec.payload.ResponseInfo;

import java.util.concurrent.CompletableFuture;

/**
 * 请求Caller
 *
 * @author: darrenfu
 * @date: 18-1-26
 */
public interface Caller<Q extends Object, R extends Object> {

    EchoCaller ECHO_CALLER = new EchoCaller();

    ResponseInfo<R> call(RequestInfo<Q> req);

    CompletableFuture<ResponseInfo<R>> asyncCall(RequestInfo<Q> req);


    class EchoCaller implements Caller<Object, String> {

        @Override
        public ResponseInfo<String> call(RequestInfo<Object> req) {
            return ResponseInfo.OK_RESP;
        }


        @Override
        public CompletableFuture<ResponseInfo<String>> asyncCall(RequestInfo<Object> req) {
            return CompletableFuture.supplyAsync(() -> ResponseInfo.OK_RESP);
        }
    }
}
