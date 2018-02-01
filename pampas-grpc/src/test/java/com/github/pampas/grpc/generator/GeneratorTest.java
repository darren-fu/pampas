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

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by darrenfu on 18-1-30.
 *
 * @author: darrenfu
 * @date: 18-1-30
 */

public class GeneratorTest {

    static final String protc = "/home/darrenfu/tools/grpc/protoc-3.4.0-linux-x86_64.exe";
    static final String protoc_java_gen = "/home/darrenfu/tools/grpc/protoc-gen-grpc-java-1.8.0-linux-x86_64.exe";

    static final String proto_file = "test.proto";
    static final String java_out_dir = System.getProperty("user.dir");

    private File protoFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(proto_file);
        File file = new File(resource.getFile());
        return file;
    }

    @Test
    public void testGenJavaCode() {
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compileRuntime() {


    }
}
