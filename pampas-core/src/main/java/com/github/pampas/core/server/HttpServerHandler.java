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

package com.github.pampas.core.server;

import com.github.pampas.common.exception.PampasException;
import com.github.pampas.common.exception.PampasRequestException;
import com.github.pampas.common.exec.Filter;
import com.github.pampas.common.exec.Worker;
import com.github.pampas.common.exec.payload.DefaultPampasRequest;
import com.github.pampas.common.exec.payload.HttpResponseHelper;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.route.Selector;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.opentracing.propagation.TextMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * HTTP请求处理器
 *
 * @author: darrenfu
 * @date: 18-1-17
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(HttpServerHandler.class);

    private final static ExecutorService workerThreadService = newBlockingExecutorsUseCallerRun(Runtime.getRuntime().availableProcessors() * 2);

    private String result = "";


    public HttpServerHandler() {
    }

    /**
     * 阻塞的ExecutorService
     *
     * @param size
     * @return
     */
    public static ExecutorService newBlockingExecutorsUseCallerRun(int size) {
        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        try {
                            executor.getQueue().put(r);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

    /*
     * 收到消息时，返回信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg == null || !(msg instanceof FullHttpRequest)) {
            result = "非法请求";
            HttpResponse httpResponse = HttpResponseHelper.buildHttpTextResponse("非法请求", HttpResponseStatus.BAD_REQUEST, HttpResponseHelper.TEXT_PLAIN);
            HttpResponseHelper.sendHttpResponse(ctx, httpResponse, false);
            return;
        }
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        if (log.isTraceEnabled()) {
            log.trace("http请求URI:{}，详情:{}", httpRequest.uri(), httpRequest.toString());
        }
        try {
            if (GatewayRequestRouter.isGatewayRequest(httpRequest.uri())) {
                HttpResponse httpResponse = GatewayRequestRouter.handleGatewayRequest(httpRequest);
                HttpResponseHelper.sendHttpResponse(ctx, httpResponse, false);
                return;
            }

            DefaultPampasRequest requestInfo = new DefaultPampasRequest(ctx, httpRequest);
            ///todo: Selector URI->ServiceName    map serviceName -> worker
            SpiContext<Selector> selectorSpiContext = SpiContext.getContext(Selector.class);
            List<Selector> selectors = selectorSpiContext.getSpiInstancesByKey(null);
            Locator locator = null;
            for (Selector selector : selectors) {
                locator = selector.select(requestInfo);
                if (locator != null) {
                    log.debug("路由匹配成功,请求路径:{},匹配结果:{}", requestInfo.originUri(), locator);
                    break;
                }
            }
            if (locator == null) {
                log.warn("请求没有匹配到合适的路由:{}", requestInfo);
                throw new PampasRequestException(HttpResponseStatus.NOT_FOUND, "请求没有匹配到合适的路由:" + requestInfo.originUri());
            }

            // 获取处理该请求的worker
            Worker worker = getWorker(locator.getWorker());
            if (log.isDebugEnabled()) {
                log.debug("本次请求使用的worker:{}", worker.getClass().getSimpleName());
            }

            // 获取Filter列表
            List<Filter> filterList = SpiContext.getContext(Filter.class).getSpiInstances();
            if (log.isDebugEnabled()) {
                log.debug("本次请求使用到的Filter:{}", filterList.stream().map(v -> v.getClass().getSimpleName()).collect(Collectors.toList()));
            }
            // worker处理请求，并返回future
            CompletableFuture<PampasResponse> executeFuture = worker.execute(requestInfo, locator, filterList);
            // worker处理完成后，发送相应
            executeFuture.whenComplete((resp, ex) -> HttpResponseHelper.sendHttpResponse(ctx, resp != null ? resp : ex, HttpUtil.isKeepAlive(httpRequest)));

        } catch (Exception ex) {
            log.warn("发生错误:{}", ex.getMessage(), ex);
            HttpResponseHelper.sendHttpResponse(ctx, ex, HttpUtil.isKeepAlive(httpRequest));
        }

    }


    private Worker getWorker(String key) {
        List<Worker> workers = SpiContext.getContext(Worker.class).getSpiInstancesByKey(key);
        if (workers == null || workers.size() < 1) {
            throw new PampasException("没有合适的Worker:" + key);
        }
        return workers.get(0);
    }


    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("@@@server1-连接的客户端地址:" + ctx.channel().remoteAddress());
//        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("@@@server1-连接inactive" + ctx.name());
        super.channelInactive(ctx);
    }


    //todo: tracer
    private static class InjectTextMap implements TextMap {
        FullHttpRequest httpRequest;

        /****
         *
         *
         *
         * //        Tracer tracer = OpenTracingContext.getTracer();
         * //        Tracer.SpanBuilder spanBuilder = tracer.buildSpan(httpRequest.uri());
         * //        Span span = spanBuilder.start();
         * //        span.setTag("method", httpRequest.method().name());
         * //        tracer.inject(span.context(), Format.Builtin.HTTP_HEADERS, new InjectTextMap(httpRequest));
         *         //span.finish();
         * //        String path = httpRequest.uri();          //获取路径
         *
         *
         */

        /**
         * @param httpRequest
         */
        InjectTextMap(FullHttpRequest httpRequest) {
            this.httpRequest = httpRequest;
        }


        @Override
        public Iterator<Map.Entry<String, String>> iterator() {
            throw new UnsupportedOperationException("iterator not support while inject");
        }

        @Override
        public void put(String key, String value) {
            log.debug("Tracer设置跟踪数据,key:{},value:{}", key, value);
            httpRequest.headers().set(key, value);
        }

    }
}
