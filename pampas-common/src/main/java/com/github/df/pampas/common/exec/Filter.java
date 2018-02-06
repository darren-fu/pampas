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

/**
 * 回调
 * Created by darrenfu on 18-1-24.
 *
 * @param <Q> the type parameter
 * @param <R> the type parameter
 * @author: darrenfu
 * @date: 18 -1-24
 */
public interface Filter<Q extends Object, R extends Object> {

    /**
     * 过滤器是否应该执行
     *
     * @param req the req
     * @return the boolean
     */
    boolean shouldFilter(RequestInfo<Q> req);

    /**
     * 请求执行前运行.
     *
     * @param req the req
     */
    void before(RequestInfo<Q> req);

    /**
     * 响应成功时执行
     *
     * @param req  the req
     * @param resp the resp
     * @return the response info
     */
    ResponseInfo<R> onSuccess(RequestInfo<Q> req, ResponseInfo<R> resp);

    /**
     * 响应异常时执行
     *
     * @param req       the req
     * @param throwable the throwable
     * @return the response info
     */
    ResponseInfo<R> onException(RequestInfo<Q> req, Throwable throwable);
}
