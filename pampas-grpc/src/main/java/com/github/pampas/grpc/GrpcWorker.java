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

import com.baidu.jprotobuf.com.squareup.protoparser.*;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.exec.AbstractWorker;
import com.github.pampas.common.exec.Caller;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.grpc.classloader.DynamicMultiClassLoader;
import com.github.pampas.grpc.client.GrpcRequest;
import com.github.pampas.grpc.client.GrpcServiceDefine;
import com.github.pampas.grpc.tools.URLTools;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.github.pampas.grpc.ClientDynamic.channel;

/**
 * Created by darrenfu on 18-2-15.
 *
 * @author: darrenfu
 * @date: 18-2-15
 */
@SpiMeta(name = "grpc", order = 20)
public class GrpcWorker extends AbstractWorker<FullHttpRequest, FullHttpResponse> {
    private static final Logger log = LoggerFactory.getLogger(GrpcWorker.class);

    private Caller grpcCaller = new GrpcCaller();

    @Override
    protected void doAfter(String threadName) {

    }

    @Override
    public CompletableFuture<PampasResponse<FullHttpResponse>> doExecute(PampasRequest<FullHttpRequest> req) throws IOException {
        log.debug("{}开始执行", GrpcWorker.class.getSimpleName());

        String protilFileName = "test.proto";
        ProtoFile protoFile = this.parserProtoFile(protilFileName);
        List<GrpcServiceDefine> serviceDefineList = this.listProtoServiceInProtoFile(protoFile);
        GrpcServiceDefine grpcServiceDefine = findGrpcService(req.path(), StringUtils.lowerCase(req.httpMethod()));
        GrpcRequest grpcRequest = new GrpcRequest();
        grpcRequest.setServiceDefine(grpcServiceDefine);

        ServerInstance serverInstance = ServerInstance.build("demo", "grpc", "localhost", 50051);


        grpcCaller.asyncCall(grpcRequest, serverInstance);

        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GrpcWorker worker = new GrpcWorker();
        //"test.proto"
//        String protilFileName = "test.proto";
//        ProtoFile protoFile = worker.parserProtoFile(protilFileName);
//        //  "/v1/example/hello"
//        worker.listProtoServiceInProtoFile(protoFile);
//        GrpcServiceDefine grpcService = worker.findGrpcService("/v1/example/hello", "post");
//        System.out.println("grpcService:" + grpcService);

        DynamicMultiClassLoader loader = DynamicMultiClassLoader.getLoader(URLTools.toUrl(Consts.JAVA_OUT_DIR));

        Class grpc = loader.loadClass("df.open.grpc.hello.HelloServiceGrpc");
        Class proto = loader.loadClass("df.open.grpc.hello.HelloServiceProto");

        Method newBlockingStub = grpc.getMethod("newBlockingStub", Channel.class);
        System.out.println(newBlockingStub);
        AbstractStub stub = (AbstractStub) newBlockingStub.invoke(grpc, channel);
        System.out.println(stub);

    }

    private ProtoFile parserProtoFile(String protilFileName) throws IOException {
        ClassLoader classLoader = GrpcWorker.class.getClassLoader();
        URL resource = classLoader.getResource(protilFileName);
        File file = new File(resource.getFile());
        ProtoFile protoFile = ProtoParser.parseUtf8(file);

        return protoFile;
    }

    private ConcurrentHashMap<String, GrpcServiceDefine> grpcServiceMap = new ConcurrentHashMap<>();


    private GrpcServiceDefine findGrpcService(String requestUri, String httpMethod) throws IOException {
        GrpcServiceDefine grpcServiceDefine = grpcServiceMap.get(httpMethod + requestUri);
        if (grpcServiceDefine == null) {
            ProtoFile protoFile = this.parserProtoFile("test.proto");
            List<GrpcServiceDefine> serviceDefineList = this.listProtoServiceInProtoFile(protoFile);
            serviceDefineList.forEach(v -> grpcServiceMap.put(v.getHttpMethod() + v.getHttpUri(), v));
            grpcServiceDefine = grpcServiceMap.get(httpMethod + requestUri);
        }
        return grpcServiceDefine;
    }


    private List<GrpcServiceDefine> listProtoServiceInProtoFile(ProtoFile protoFile) {
        List<GrpcServiceDefine> result = new ArrayList();

        boolean javaMultipleFiles = false;
        String javaOuterClassName = "";
        String javaPkgName = "";
        for (OptionElement optionElement : protoFile.options()) {
            if ("java_multiple_files".equals(optionElement.name())) {
                javaMultipleFiles = Boolean.valueOf(optionElement.value().toString());
            }
            if ("java_outer_classname".equals(optionElement.name())) {
                javaOuterClassName = (String) optionElement.value();
            }
            if ("java_package".equals(optionElement.name())) {
                javaPkgName = (String) optionElement.value();
            }
        }

        // Service
        for (ServiceElement serviceElement : protoFile.services()) {
            System.out.println("serviceElement:" + serviceElement);
            // method
            for (RpcElement rpcElement : serviceElement.rpcs()) {
                // http option
                for (OptionElement optionElement : rpcElement.options()) {
                    // google http option
                    if (optionElement.name().equals("google.api.http")) {
                        Map<String, String> defineMap = (Map) optionElement.value();
                        String[] methods = new String[]{"get", "put", "post", "delete", "patch"};
                        // http methods
                        for (String httpMethod : methods) {
                            if (defineMap.containsKey(httpMethod)) {
                                String httpUri = defineMap.get(httpMethod);
                                GrpcServiceDefine grpcServiceDefine = new GrpcServiceDefine();
                                grpcServiceDefine.setServiceName(serviceElement.qualifiedName());
                                grpcServiceDefine.setJavaOuterClassName(javaOuterClassName);
                                grpcServiceDefine.setJavaPkgName(javaPkgName);

                                grpcServiceDefine.setRpcClass(javaPkgName + "." + serviceElement.qualifiedName() + "Grpc");
                                grpcServiceDefine.setProtoClass(javaPkgName + "." + javaOuterClassName);
                                grpcServiceDefine.setMethod(rpcElement.name());
                                if (javaMultipleFiles) {
                                    grpcServiceDefine.setReqPojoClass(javaPkgName + "." + rpcElement.requestType().name());
                                    grpcServiceDefine.setRespPojoClass(javaPkgName + "." + rpcElement.responseType().name());
                                } else {
                                    // inner class
                                    grpcServiceDefine.setReqPojoClass(javaPkgName + "." + javaOuterClassName + "$" + rpcElement.requestType().name());
                                    grpcServiceDefine.setRespPojoClass(javaPkgName + "." + javaOuterClassName + "$" + rpcElement.responseType().name());
                                }


                                grpcServiceDefine.setHttpMethod(httpMethod);
                                grpcServiceDefine.setHttpUri(httpUri);
                                result.add(grpcServiceDefine);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


}
