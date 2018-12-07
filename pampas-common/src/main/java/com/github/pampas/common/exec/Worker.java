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
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.Locator;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 工作者
 * 处理Pampas收取的请求
 * Created by darrenfu on 18-1-24.
 *
 * @param <Q> the type parameter
 * @param <R> the type parameter
 * @author: darrenfu
 * @date: 18 -1-24
 */
@Spi(scope = Scope.SINGLETON)
public interface Worker<Q extends Object, R extends Object> {

    /**
     * 工作者名称
     *
     * @return
     */
    default String name() {
        SpiMeta spiMeta = getClass().getAnnotation(SpiMeta.class);
        return (spiMeta != null && !"".equals(spiMeta.name())) ? spiMeta.name() : getClass().getSimpleName();
    }


    /**
     * 执行请求和过滤器，返回Future
     *
     * @param req    the req
     * @param locator
     * @param filterList the filter
     * @return the future
     */
    Future<PampasResponse> execute(PampasRequest<Q> req, Locator locator, List<Filter<Q, R>> filterList);

//    Future<Rsp> execute(Req req, BiConsumer<Req, Rsp> success, BiConsumer<Req, Throwable> failed);
}
