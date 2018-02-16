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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * Created by darrenfu on 18-2-1.
 *
 * @author: darrenfu
 * @date: 18-2-1
 */
public class DynamicJarCreator {

    private static final Logger log = LoggerFactory.getLogger(DynamicJarCreator.class);

    private static URL toUrl(String path) {
        File classFile = new File(path);
        try {
            return classFile.toURI().toURL();
        } catch (MalformedURLException e) {
            log.error("文件路径错误:{}", path, e);
            throw new RuntimeException("文件路径错误:" + path + ",Error:" + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {

        DynamicJarCreator.createJar("/home/darrenfu/IdeaProjects/pampas/pampas-grpc/df/open/grpc/hello");

    }

    /**
     * 动态生成Jar包
     */
    public static File createJar(String classDir) throws Exception {
        URL root = toUrl(classDir);

        JarOutputStream out = null;
//        final File jar = File.createTempFile("grpc-test-", ".jar", new File(System.getProperty("java.io.tmpdir")));
        final File jar = File.createTempFile("grpc-test-", ".jar", new File(classDir));
        System.out.println(jar.getAbsolutePath());
//        jar.deleteOnExit();

        try {
            File path = new File(root.toURI());
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().putValue("Manifest-Version", "1.0");
            manifest.getMainAttributes().putValue("Created-By", "JarPackageUtil");
            out = new JarOutputStream(new FileOutputStream(jar), manifest);
            writeBaseFile(out, path, "");
            System.out.println(path.getAbsolutePath());
        } finally {
            out.flush();
            out.close();
        }
        return jar;
    }

    /**
     * 递归添加.class文件
     */
    private static void writeBaseFile(JarOutputStream out, File file, String base) throws IOException {
        if (file.isDirectory()) {
            File[] fl = file.listFiles();
            if (base.length() > 0) {
                base = base + File.separatorChar;
            }
            for (int i = 0; i < fl.length; i++) {
                writeBaseFile(out, fl[i], base + fl[i].getName());
            }
        } else if (file.getName().endsWith(".class")) {
            System.out.println("class:" + file.getName());
            out.putNextEntry(new JarEntry(base));
            FileInputStream in = null;
            try {
                in = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int n = in.read(buffer);
                while (n != -1) {
                    out.write(buffer, 0, n);
                    n = in.read(buffer);
                }
            } finally {
                in.close();
            }
        }
    }


}
