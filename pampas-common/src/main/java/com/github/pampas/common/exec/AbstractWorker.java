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
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.tools.ResponseTools;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 请求执行器抽象类
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public abstract class AbstractWorker<Q extends HttpRequest, R extends Object> implements Worker<Q, R> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected abstract void doAfter(String threadName);

    public abstract CompletableFuture<PampasResponse<R>> doExecute(PampasRequest<Q> req, Locator locator) throws IOException;

    @Override
    public Future<PampasResponse> execute(PampasRequest<Q> req, Locator locator, List<Filter<Q, R>> execFilterList) {
        CompletableFuture<PampasResponse> workerFuture = CompletableFuture.supplyAsync(() -> {

            try {
                List<Filter<Q, R>> filterList = execFilterList;
                FilterContext.CURRENT.resetChain();

                //执行过滤器before
                for (Filter filter : filterList) {
                    FilterChain filterChain = FilterContext.CURRENT.chain(filter.getClass().getSimpleName());
                    if (!filterChain.isFilterChainStop()) {
                        filter.before(req, locator, filterChain);
                    } else if (filterChain.getResponse() != null) {
                        // filter终端 并且返回response
                        this.sendPampasResponse(req.channelHandlerContext(), filterChain.getResponse(), req.isKeepalive());
                        return filterChain.getResponse();
                    }
                }
                CompletableFuture<PampasResponse<R>> future = null;

                // 调用具体的worker 处理此request
                future = doExecute(req, locator);

                String threadName = Thread.currentThread().getName();
                PampasResponse<R> pampasResponse = future.get();
                log.debug("获取响应:{}", pampasResponse);

                //反转过滤器顺序
                Collections.reverse(filterList);

                //执行过滤器 onSuccess和onException
                for (Filter filter : filterList) {
                    FilterChain filterChain = FilterContext.CURRENT.chain(filter.getClass().getSimpleName());
                    if (!filterChain.isFilterChainStop()) {
                        if (pampasResponse.success()) {
                            filter.onSuccess(req, locator, pampasResponse, filterChain);
                        } else if (pampasResponse.exception() != null) {
                            filter.onException(req, locator, pampasResponse.exception(), filterChain);
                        }
                    } else if (filterChain.getResponse() != null) {
                        // filter终端 并且返回response
                        pampasResponse = filterChain.getResponse();
                        break;
                    }
                }
                FilterContext.CURRENT.resetChain();
                this.sendPampasResponse(req.channelHandlerContext(), pampasResponse, req.isKeepalive());
                EventExecutor executor = req.channelHandlerContext().executor();
                executor.submit(() -> doAfter(threadName));
                return pampasResponse;
            } catch (Exception ex) {
                log.error("处理请求发生错误:{}", ex.getMessage(), ex);
                ResponseTools.sendResp(req.channelHandlerContext(), ex, req.isKeepalive());
                return new PampasResponse.ExceptionPampasResponse(ex.getMessage(), ex);
            } finally {
                ReferenceCountUtil.release(req.requestData());
            }
        });
        return workerFuture;
    }

    private void sendPampasResponse(ChannelHandlerContext ctx, PampasResponse pampasResponse, boolean keepalive) {
        if (pampasResponse.success()) {
            log.debug("成功获取响应:{}", pampasResponse);
            ResponseTools.sendResp(ctx, pampasResponse.responseData(), keepalive);
        } else {
            log.error("请求失败:{}", pampasResponse.exception());
            ResponseTools.sendResp(ctx, pampasResponse.exception(), keepalive);
        }
    }


}
