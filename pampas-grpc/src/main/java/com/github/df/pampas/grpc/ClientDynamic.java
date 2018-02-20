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

package com.github.df.pampas.grpc;

import com.github.df.pampas.grpc.classloader.DynamicMultiClassLoader;
import com.github.df.pampas.grpc.tools.URLTools;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.*;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by darrenfu on 18-1-31.
 *
 * @author: darrenfu
 * @date: 18-1-31
 */
@SuppressWarnings("Duplicates")
public class ClientDynamic {
    static String host = "localhost";
    static Integer server_port = 50051;
    static ManagedChannel channel;
    private static CallOptions callOption = CallOptions.DEFAULT; // TODO 需要配置线程池时使用
    static long timeout = 5000L;

    static {
        channel = ManagedChannelBuilder.forAddress(host, server_port).usePlaintext(true).build();
    }

    public static void main(String[] args) throws Exception {
        autoCall();
//        Thread.sleep(3_000L);
    }

    private static URL toUrl(String path) {

        File classFile = new File(path);
        try {
            return classFile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException("文件路径错误:" + path + ",Error:" + e.getMessage());
        }
    }

    @Test
    public void testLoad() throws Exception {
        Loader.getLoader().addURL(toUrl("/home/darrenfu/IdeaProjects/pampas/pampas-grpc/df/open/grpc/hello/grpc-test-229014610914606914.jar"));
        Class proto = Loader.load("df.open.grpc.hello.HelloServiceProto");
        Class protoReq = Loader.load("df.open.grpc.hello.HelloServiceProto$HelloReq");
        Class<?> protoClz = Class.forName("df.open.grpc.hello.HelloServiceProto", false, Loader.getLoader());

        Class<?> reqClz = Class.forName("df.open.grpc.hello.HelloServiceProto$HelloReq", false, Loader.getLoader());
        System.out.println(reqClz);
    }

    public static <ReqT, RespT> void autoCall() throws Exception {
        DynamicMultiClassLoader loader = DynamicMultiClassLoader.getLoader(toUrl("/home/darrenfu/IdeaProjects/pampas/pampas-grpc/df/open/grpc/hello/grpc-test-229014610914606914.jar"));
        Class grpc = loader.load("df.open.grpc.hello.HelloServiceGrpc");
        Class proto = loader.load("df.open.grpc.hello.HelloServiceProto");
        Method getSayHelloMethod = grpc.getDeclaredMethod("getSayHelloMethod");
        MethodDescriptor<ReqT, RespT> methodDescriptor = (MethodDescriptor) getSayHelloMethod.invoke(grpc);

        ClientCall<ReqT, RespT> call =
                new ForwardingClientCall.SimpleForwardingClientCall(channel.newCall(methodDescriptor, callOption.withDeadlineAfter(timeout, TimeUnit.MILLISECONDS))) {

                    public void start(Listener responseListener, Metadata headers) {
                        System.out.println("start call......");
                        super.start(responseListener, headers);
                    }
                };
//        ClientCalls.asyncUnaryCall(call, (ReqT) req.newInstance(), responseFuture);
        Class<?> reqClz = Class.forName("df.open.grpc.hello.HelloServiceProto$HelloReq", false, loader);
        Constructor<?> constructor = reqClz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println(constructor.isAccessible());

        RespT respT = ClientCalls.blockingUnaryCall(call, (ReqT) constructor.newInstance());
        System.out.println(respT);
        System.out.println("XXXXXXXxx");

    }


    @Test
    public void doCallSync() throws Exception {
        Class grpc = Loader.load("df.open.grpc.hello.HelloServiceGrpc");
        Class proto = Loader.load("df.open.grpc.hello.HelloServiceProto");

        Method futureStub = grpc.getMethod("newFutureStub", Channel.class);

        AbstractStub stub = (AbstractStub) futureStub.invoke(grpc, channel);
        System.out.println(stub);
        Class<?> reqClz = Class.forName("df.open.grpc.hello.HelloServiceProto$HelloReq", false, Loader.getLoader());
        Constructor<?> constructor = reqClz.getDeclaredConstructor();
        constructor.setAccessible(true);

        Method sayHello = stub.getClass().getMethod("sayHello", reqClz);
        ListenableFuture future = (ListenableFuture) sayHello.invoke(stub, constructor.newInstance());
        System.out.println(future.isDone());
        System.out.println(future.get());
        System.out.println(future.isDone());

    }


    @Test
    public void doCallAsync() throws Exception {
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
