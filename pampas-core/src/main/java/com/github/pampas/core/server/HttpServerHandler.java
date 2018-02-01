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

import com.github.pampas.core.CollectionTools;
import com.github.pampas.core.http.HttpClient;
import com.github.pampas.core.tracer.OpenTracingContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMap;
import io.opentracing.propagation.TextMapExtractAdapter;
import io.opentracing.propagation.TextMapInjectAdapter;
import jdk.nashorn.internal.codegen.MapCreator;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
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
    private HttpClient client;

    public HttpServerHandler(HttpClient httpClient) {
        this.client = httpClient;
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
        log.info("http request:{}", httpRequest);
//        System.out.println(((FullHttpRequest) msg).content().readableBytes());

        Tracer.SpanBuilder spanBuilder = tracer.buildSpan(httpRequest.getUri());
        Span span = spanBuilder.start();
        span.setTag("method", httpRequest.getMethod().name());
        tracer.inject(span.context(), Format.Builtin.HTTP_HEADERS, new TextMap() {

            @Override
            public Iterator<Map.Entry<String, String>> iterator() {
                log.debug("获取TextMap遍历器");
                return httpRequest.headers().iterator();
            }

            @Override
            public void put(String key, String value) {
                log.debug("Tracer设置跟踪数据,key:{},value:{}", key, value);
                httpRequest.headers().set(key, value);
            }
        });
        span.log(System.currentTimeMillis(), "span_start");
        span.log(System.currentTimeMillis(), CollectionTools.toMap("handler", "netty"));


        //span.finish();


        try {
            String path = httpRequest.getUri();          //获取路径
            System.out.println("path:" + path);
            String body = getBody(httpRequest);     //获取参数
            HttpMethod method = httpRequest.getMethod();//获取请求方法
            //如果不是这个路径，就直接返回错误
            if (!"/test".equalsIgnoreCase(path)) {
                Thread.sleep(10000L);
                result = "非法请求!";
                send(ctx, result, HttpResponseStatus.BAD_REQUEST);
                return;
            }
            client.exec(ctx, httpRequest, tracer, span);
//            AsyncTool.doRelease(ctx, httpRequest);
        } catch (Exception e) {
            System.out.println("@@@server1-处理请求失败!");
            e.printStackTrace();
        } finally {
        }
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
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("@@@server1-连接的客户端地址:" + ctx.channel().remoteAddress());
//        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("@@@server1-连接inactive" + ctx.name());
        super.channelInactive(ctx);
    }
}
