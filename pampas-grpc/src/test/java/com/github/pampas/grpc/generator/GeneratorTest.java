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

package com.github.pampas.grpc.generator;

import com.github.df.pampas.grpc.runtime.CompileApi;
import com.github.df.pampas.grpc.Consts;
import com.github.df.pampas.grpc.runtime.DynamicJarCreator;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by darrenfu on 18-1-30.
 *
 * @author: darrenfu
 * @date: 18-1-30
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneratorTest {

    static final String protc = "/home/darrenfu/tools/grpc/protoc-3.4.0-linux-x86_64.exe";
    static final String protoc_java_gen = "/home/darrenfu/tools/grpc/protoc-gen-grpc-java-1.8.0-linux-x86_64.exe";

    static final String proto_file = "test.proto";
    //    static final String java_out_dir = System.getProperty("user.dir");
    static final String java_out_dir = Consts.JAVA_OUT_DIR;

    private File protoFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(proto_file);
        File file = new File(resource.getFile());
        return file;
    }

    @BeforeClass
    public static void initDir() {
        File dir = new File(java_out_dir);
        dir.deleteOnExit();
        dir.mkdirs();
        dir.setReadable(true);
        dir.setWritable(true);
        dir.deleteOnExit();
        System.out.println("dir:" + dir);
    }


    @Test
    public void testStep1GenJavaCode() {
        System.out.println("java_out_dir:" + java_out_dir);
        File proto = protoFile();
        System.out.println("proto file dir:" + proto.getParent());
        System.out.println("proto file name:" + proto.getName());
        Runtime runtime = Runtime.getRuntime();
        try {
            String cmd1 = protc + " --java_out=" + java_out_dir + " --proto_path=" + proto.getParent() + " " + proto.getName();
            Process process1 = runtime.exec(cmd1);
            process1.waitFor();
            System.out.println("CMD_1:" + cmd1);

            String cmd2 = protc + " --plugin=protoc-gen-grpc-java=" + protoc_java_gen
                    + " --grpc-java_out=" + java_out_dir + " --proto_path=" + proto.getParent() + " " + proto.getName();
            Process process2 = runtime.exec(cmd2);
            process2.waitFor();
            System.out.println("CMD_2:" + cmd2);
            System.out.println("################成功为Proto生成Java类...");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testStep2CompileRuntime() throws Exception {
        CompileApi.compile(Consts.JAVA_OUT_DIR);
        System.out.println("################成功编译Class...");
        System.out.println("");
        System.out.println("");
    }

    @Test
    public void testStep3CreateJarRuntime() throws Exception {
        DynamicJarCreator.createJar(Consts.JAVA_OUT_DIR);
        System.out.println("################成功生成JAR...");
        System.out.println("");
        System.out.println("");
    }
}
