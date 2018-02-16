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

package com.github.df.pampas.grpc.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by darrenfu on 18-1-30.
 *
 * @author: darrenfu
 * @date: 18-1-30
 */
public class CompilerJavac {
    private static String source_dir = "/home/darrenfu/IdeaProjects/pampas/pampas-grpc/df/open/grpc/hello";
    private static String dest_dir = source_dir;
    private static String claspath = System.getProperty("java.class.path");

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("java.class.path"));

        doCompileWithJavac("HelloServiceProto");
        doCompileWithJavac("HelloServiceGrpc");
    }

    public static void doCompileWithJavac(String javaFileName) {

        String cmd = "/opt/java/jdk1.8.0_121/bin/javac -cp " + ".:" + source_dir + ":" + claspath
//            Process process = runtime.exec("/opt/java/jdk1.8.0_121/bin/javac "
                + " -d " + dest_dir
                + " " + source_dir + "/" + javaFileName + ".java";

        System.out.println(cmd);

        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(cmd);
            BufferedReader stdout = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            int count = 0;
            String output = "";
            String line = null;
            while ((line = stdout.readLine()) != null) {
                output += line;
            }
            process.waitFor();
            System.out.println("compile finish");
            System.out.println(output);
            ;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
