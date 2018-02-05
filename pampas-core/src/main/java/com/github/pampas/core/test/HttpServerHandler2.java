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

package com.github.pampas.core.test;

import com.github.df.pampas.common.tracer.OpenTracingContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMap;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

/**
 * HTTP请求处理器
 *
 * @author: darrenfu
 * @date: 18-1-17
 */
public class HttpServerHandler2 extends ChannelInboundHandlerAdapter {
    private String result = "";
    static LongAdder count = new LongAdder();

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    /*
     * 收到消息时，返回信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        count.increment();
        System.out.println("server2-count:" + count.sum());

        if (!(msg instanceof FullHttpRequest)) {
            result = "未知请求!";
            send(ctx, result, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        FullHttpRequest httpRequest = (FullHttpRequest) msg;


        Tracer tracer = OpenTracingContext.getTracer();
        Tracer.SpanBuilder spanBuilder = tracer.buildSpan("/server2" + httpRequest.getUri());

        SpanContext spanCtx = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMap() {
            @Override
            public Iterator<Map.Entry<String, String>> iterator() {
                System.out.println("Trancer iterator...");
                return httpRequest.headers().iterator();
            }

            @Override
            public void put(String key, String value) {
                System.out.println("XXXXXXXXXXXXXXXXX");
            }
        });
        if (spanCtx != null) {
            spanBuilder.asChildOf(spanCtx);
        }
        Span span = spanBuilder.start();


        try {
            String path = httpRequest.getUri();          //获取路径
            String body = getBody(httpRequest);     //获取参数
            HttpMethod method = httpRequest.getMethod();//获取请求方法
            //如果不是这个路径，就直接返回错误

//            Thread.sleep(10000L);
            result = "非法请求!";
            send(ctx, result, HttpResponseStatus.OK);
            return;

        } catch (Exception e) {
            System.out.println("#server2-处理请求失败!");
            e.printStackTrace();
        } finally {
            //释放请求
            span.finish();
            httpRequest.release();
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
//        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, context.getBytes().length);
        response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        ctx.write(response);
        ctx.flush();//.addListener(ChannelFutureListener.CLOSE);
    }

    static LongAdder conn = new LongAdder();

    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        conn.increment();
        System.out.println("server2-conn:" + conn.sum());
//        System.out.println("#server2-连接的客户端地址:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("#server2-连接inactive" + ctx.name());
        super.channelInactive(ctx);
    }
}
