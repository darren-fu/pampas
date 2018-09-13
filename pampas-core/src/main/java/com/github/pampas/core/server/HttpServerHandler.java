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
import com.github.pampas.common.exec.Worker;
import com.github.pampas.common.exec.payload.DefaultPampasRequest;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.route.Selector;
import com.github.pampas.common.tracer.OpenTracingContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.opentracing.Tracer;
import io.opentracing.propagation.TextMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

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
        Tracer tracer = OpenTracingContext.getTracer();

        if (!(msg instanceof FullHttpRequest)) {
            result = "未知请求!";
            send(ctx, result, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        if (log.isTraceEnabled()) {
            log.trace("http request:{}", httpRequest);
        }
        System.out.println(httpRequest.uri());
//        Tracer.SpanBuilder spanBuilder = tracer.buildSpan(httpRequest.uri());
//        Span span = spanBuilder.start();
//        span.setTag("method", httpRequest.method().name());
//        tracer.inject(span.context(), Format.Builtin.HTTP_HEADERS, new InjectTextMap(httpRequest));
        //span.finish();
        String path = httpRequest.uri();          //获取路径
        //如果不是这个路径，就直接返回错误
        DefaultPampasRequest requestInfo = new DefaultPampasRequest(ctx, httpRequest);

        ///todo: Selector URI->ServiceName    map serviceName -> worker
        SpiContext<Selector> selectorSpiContext = SpiContext.getContext(Selector.class);
        List<Selector> selectors = selectorSpiContext.getSpiInstancesByKey(null);
        Locator locator = null;
        for (Selector selector : selectors) {
            locator = selector.select(requestInfo);
            if (locator != null) {
                break;
            }
        }
        if (locator == null) {
            log.warn("请求没有匹配到合适的路由:{}", requestInfo);
            // todo: 需要返回给client
            throw new PampasException("请求没有匹配到合适的路由:" + requestInfo.originUri());
        }

        Worker worker = getWorker(locator.getWorker());
        worker.execute(requestInfo, locator, null);
    }


    private Worker getWorker(String key) {
        List<Worker> workers = SpiContext.getContext(Worker.class).getSpiInstancesByKey(key);
        if (workers == null || workers.size() < 1) {
            throw new PampasException("没有合适的Worker:" + key);
        }
        return workers.get(0);
    }

    /**
     * 获取body参数
     *
     * @param request
     * @return
     */
    private String getBody(FullHttpRequest request) {
        ByteBuf buf = request.content();
        return buf.toString(CharsetUtil.UTF_8);
    }

    /**
     * 发送的返回值
     *
     * @param ctx     返回
     * @param context 消息
     * @param status  状态
     */
    private void send(ChannelHandlerContext ctx, String context, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
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


    private static class InjectTextMap implements TextMap {
        FullHttpRequest httpRequest;

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
