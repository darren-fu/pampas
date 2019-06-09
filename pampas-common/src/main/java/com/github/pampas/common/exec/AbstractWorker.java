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
import com.github.pampas.common.exec.payload.PampasResponseHelper;
import com.github.pampas.common.route.Locator;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 请求执行器抽象类
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public abstract class AbstractWorker<Q extends HttpRequest, R extends Object> implements Worker<Q, R> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected abstract void doAfter();

    public abstract CompletableFuture<PampasResponse<R>> doExecute(PampasRequest<Q> req, Locator locator) throws IOException;

    public PampasResponse execute(PampasRequest<Q> req, Locator locator, FilterChain filterChain) {
        try {
            PampasResponse beforeCallResponse = filterChain.executeBeforeCall(req, locator);

            if (filterChain.isFilterChainStop()) {
                return beforeCallResponse;
            }


            CompletableFuture<PampasResponse<R>> future = null;

            // 调用具体的worker 处理此request
            future = doExecute(req, locator);
            PampasResponse<R> gatewayResponse = future.join();

            log.debug("获取响应:{}", gatewayResponse);
            PampasResponse executeAfterResponse = filterChain.executeAfter(req, locator, gatewayResponse);
            if(filterChain.isFilterChainStop()){
                return executeAfterResponse;
            }
            EventExecutor executor = req.channelHandlerContext().executor();
            executor.submit(() -> doAfter());
            return gatewayResponse;
        } catch (Exception ex) {
            log.error("处理请求发生错误:{}", ex.getMessage(), ex);
            return PampasResponseHelper.buildFailedResponse(ex);
        } finally {
            ReferenceCountUtil.release(req.requestData());
        }
    }




}
