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

package com.github.pampas.common.exec;


import com.github.pampas.common.discover.ServerInstance;

import java.util.concurrent.CompletableFuture;

/**
 * 请求者
 *
 * @param <Q> the type parameter
 * @param <R> the type parameter
 * @author: darrenfu
 * @date: 18 -1-26
 */
public interface Caller<Q extends Object, R extends Object> {

    /**
     * 同步请求，直接返回结果
     *
     * @param req            the req
     * @param serverInstance the server instance
     * @return the r
     */
    R call(Q req, ServerInstance serverInstance);

    /**
     * 异步请求，返回CompletableFuture
     *
     * @param req            the req
     * @param serverInstance the server instance
     * @return the completable future
     */
    CompletableFuture<R> asyncCall(Q req, ServerInstance serverInstance);
}
