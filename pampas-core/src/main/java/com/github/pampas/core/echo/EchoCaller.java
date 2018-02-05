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

package com.github.pampas.core.echo;

import com.github.df.pampas.common.exec.Caller;
import com.github.df.pampas.common.exec.payload.RequestInfo;

import java.util.concurrent.CompletableFuture;

/**
 * 直接返回的caller
 */
public class EchoCaller implements Caller<Object, String> {

    @Override
    public String call(RequestInfo<Object> req) {
        return "OK";
    }

    @Override
    public CompletableFuture<String> asyncCall(RequestInfo<Object> req) {
        return CompletableFuture.supplyAsync(() -> "OK");
    }
}