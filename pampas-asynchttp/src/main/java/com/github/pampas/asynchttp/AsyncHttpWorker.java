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

package com.github.pampas.asynchttp;

import com.github.pampas.asynchttp.tools.AhcTools;
import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.discover.ServerContext;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.exception.NoServiceInstanceFoundException;
import com.github.pampas.common.exception.PampasException;
import com.github.pampas.common.exec.AbstractWorker;
import com.github.pampas.common.exec.payload.DefaultPampasResponse;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.loadbalance.LoadBalancer;
import com.github.pampas.common.route.Locator;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.commons.collections4.CollectionUtils;
import org.asynchttpclient.Response;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
@SpiMeta(name = "http-async", key = PampasConsts.Worker.HTTP, order = 20)
public class AsyncHttpWorker extends AbstractWorker<FullHttpRequest, FullHttpResponse> {


    public AsyncHttpWorker() {
    }


    @Override
    protected void doAfter(String name) {
        System.out.println("AsyncHttpWorker:" + Thread.currentThread().getName());
    }


    private ServerInstance doLoadBalance(Locator locator) {
        SpiContext<LoadBalancer> loadBalancerContext = SpiContext.getContext(LoadBalancer.class);
        LoadBalancer loadBalancer = loadBalancerContext.getSpiInstanceByName(locator.getLoadBalancer());

        List<LoadBalancer> loadBalancerList = Collections.EMPTY_LIST;
        if (loadBalancer == null) {
            loadBalancerList = loadBalancerContext.getSpiInstances();
            loadBalancer = CollectionUtils.isNotEmpty(loadBalancerList) ? loadBalancerList.get(0) : null;
        }

        List<ServerContext> serverContextList = SpiContext.getContext(ServerContext.class).getSpiInstances();
        List<ServerInstance> serverList = Collections.EMPTY_LIST;
        for (ServerContext serverContext : serverContextList) {
            serverList = serverContext.getServerList(locator.getServiceName());
            if (serverList != null && serverList.size() > 0) {

                log.debug("服务<{}>,实例<{}>个列表:{}", locator.getServiceName(), serverList.size(), serverList);
                if (loadBalancer == null) {
                    return serverList.get(0);
                }
                ServerInstance serverInstance = loadBalancer.choose(serverContext, locator, Collections.EMPTY_SET);
                log.debug("根据服务映射Locator:{},获取服务实例:{}", locator, serverInstance);
                return serverInstance;
            }
        }
        throw new NoServiceInstanceFoundException("无法找到可用的服务实例" + locator.getServiceName() + "/" + locator.getLoadBalancer());
    }


    @Override
    public CompletableFuture<PampasResponse<FullHttpResponse>> doExecute(PampasRequest<FullHttpRequest> req, Locator locator) {
        AsyncHttpCaller caller = AsyncHttpCallerFactory.getHttpCaller(locator.getServiceName());
        FullHttpRequest requestData = req.requestData();
        // 负载均衡
        ServerInstance serverInstance = this.doLoadBalance(locator);
        if (serverInstance == null) {
            throw new PampasException("没有可用服务:" + locator.getServiceName());
        }

        AsyncHttpRequest asyncHttpRequest = new AsyncHttpRequest(requestData, locator.getMappedPath());
        CompletableFuture<Response> future = caller.asyncCall(asyncHttpRequest, serverInstance);

        CompletableFuture<PampasResponse<FullHttpResponse>> responseFuture = future.thenApply(response -> {
            log.debug("<OK>请求URI:{}，路由目标:{}，请求内容：{}，响应内容:{}", requestData.uri(), serverInstance.getUri(), requestData.content().toString(UTF_8), AhcTools.responseToString(response));
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(requestData.protocolVersion(), HttpResponseStatus.valueOf(response.getStatusCode()),
                    //response.getResponseBodyAsByteBuffer是HeapByteBuf
                    // zero-copy 设置FullHttpResponse的body
                    Unpooled.wrappedBuffer(response.getResponseBodyAsByteBuffer()));
            fullHttpResponse.headers().set(response.getHeaders());
            DefaultPampasResponse<FullHttpResponse> defaultResponseInfo = new DefaultPampasResponse();
            defaultResponseInfo.setResponseData(fullHttpResponse);
            defaultResponseInfo.setSuccess(true);
            return (PampasResponse<FullHttpResponse>) defaultResponseInfo;
        }).exceptionally(ex -> {
            log.warn("<FAILED>请求URI:{}，路由目标:{}，请求内容：{}，出现异常:{}", requestData.uri(), serverInstance.getUri(), requestData.content().toString(UTF_8), ex.getMessage(), ex);
            DefaultPampasResponse<FullHttpResponse> defaultResponseInfo = new DefaultPampasResponse();
            defaultResponseInfo.setException(ex);
            defaultResponseInfo.setSuccess(false);
            return defaultResponseInfo;
        });

        return responseFuture;
    }


}
