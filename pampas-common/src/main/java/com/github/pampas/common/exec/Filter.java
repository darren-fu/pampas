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

import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;
import com.github.pampas.common.route.Locator;

/**
 * worker回调、过滤器
 * Created by darrenfu on 18-1-24.
 *
 * @param <Q> the type parameter
 * @param <R> the type parameter
 * @author: darrenfu
 * @date: 18 -1-24
 */
@Spi(scope = Scope.SINGLETON, desc = "过滤器")
public interface Filter<Q extends Object, R extends Object> {

    /**
     * 请求执行前运行.
     *
     * @param req         请求详情
     * @param locator     路由结果
     * @param filterChain 过滤器链-可控制终止后续过滤器，直接返回结果
     */
    void before(PampasRequest<Q> req, Locator locator, FilterChain filterChain);

    /**
     * 响应成功时执行
     *
     * @param req         请求详情
     * @param locator     路由结果
     * @param resp        upstream的响应结果
     * @param filterChain 过滤器链-可控制终止后续过滤器，直接返回结果
     */
    void onSuccess(PampasRequest<Q> req, Locator locator, PampasResponse<R> resp, FilterChain filterChain);

    /**
     * 响应异常时执行
     *
     * @param req         请求详情
     * @param locator     路由结果
     * @param throwable   upstream的响应结果
     * @param filterChain 过滤器链-可控制终止后续过滤器，直接返回结果
     */
    void onException(PampasRequest<Q> req, Locator locator, Throwable throwable, FilterChain filterChain);
}
