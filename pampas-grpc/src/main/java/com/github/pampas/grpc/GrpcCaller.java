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

package com.github.pampas.grpc;

import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.exec.Caller;
import com.github.pampas.grpc.classloader.DynamicMultiClassLoader;
import com.github.pampas.grpc.client.GrpcRequest;
import com.github.pampas.grpc.client.GrpcServiceDefine;
import com.github.pampas.grpc.tools.URLTools;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.AbstractStub;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by darrenfu on 18-2-15.
 *
 * @author: darrenfu
 * @date: 18-2-15
 */
public class GrpcCaller implements Caller<GrpcRequest, Object> {
    @Override
    public Object call(GrpcRequest req, ServerInstance serverInstance) {
        return null;
    }

    @Override
    public CompletableFuture<Object> asyncCall(GrpcRequest req, ServerInstance serverInstance) {
//        try {
//            doCallAsync();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Channel channel = this.createChannel(serverInstance);

        DynamicMultiClassLoader loader = DynamicMultiClassLoader.getLoader(URLTools.toUrl(Consts.JAVA_OUT_DIR));

        try {
            GrpcServiceDefine serviceDefine = req.getServiceDefine();
            Class grpcClz = loader.load(serviceDefine.getRpcClass());
            Method newBlockingStub = grpcClz.getMethod("newBlockingStub", Channel.class);
            AbstractStub stub = (AbstractStub) newBlockingStub.invoke(grpcClz, channel);
            stub.withDeadlineAfter(1_000, TimeUnit.MILLISECONDS);
            System.out.println(stub);

            Class reqClz = loader.load(serviceDefine.getReqPojoClass());
//            Class<?> reqClz = Class.forName("df.open.grpc.hello.HelloServiceProto$HelloReq", false, loader);

            Constructor<?> constructor = reqClz.getDeclaredConstructor();
            constructor.setAccessible(true);

            Method interfaceMethod = stub.getClass().getMethod(serviceDefine.getMethod(), reqClz);
            /// todo: 扩展支撑 build方式 初始化数据
            Object invoke = interfaceMethod.invoke(stub, constructor.newInstance());
            System.out.println("invoke:" + invoke.getClass());
            System.out.println("invoke:" + invoke);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        return null;
    }

    static String host = "localhost";
    static Integer server_port = 50051;

    private Channel createChannel(ServerInstance serverInstance) {
        Channel channel = ManagedChannelBuilder
                .forAddress(serverInstance.getHost(), serverInstance.getPort())
                .usePlaintext(true).build();
        return channel;
//        return ManagedChannelBuilder.forAddress(host, server_port).usePlaintext(true).build();
    }

    public void doCallAsync() throws Exception {
        Channel channel = ManagedChannelBuilder.forAddress(host, server_port).usePlaintext(true).build();
        DynamicMultiClassLoader loader = DynamicMultiClassLoader.getLoader(URLTools.toUrl(Consts.JAVA_OUT_DIR));

        Class grpc = loader.load("df.open.grpc.hello.HelloServiceGrpc");
        Class proto = loader.load("df.open.grpc.hello.HelloServiceProto");

        Method newBlockingStub = grpc.getMethod("newBlockingStub", Channel.class);

        AbstractStub stub = (AbstractStub) newBlockingStub.invoke(grpc, channel);
        System.out.println(stub);
        Class<?> reqClz = Class.forName("df.open.grpc.hello.HelloServiceProto$HelloReq", false, loader);
        Constructor<?> constructor = reqClz.getDeclaredConstructor();
        constructor.setAccessible(true);

        Method sayHello = stub.getClass().getMethod("sayHello", reqClz);
        Object invoke = sayHello.invoke(stub, constructor.newInstance());
        System.out.println(invoke);
    }
}