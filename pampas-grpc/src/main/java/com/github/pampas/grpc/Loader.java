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

import com.github.pampas.grpc.classloader.DynamicMultiClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by darrenfu on 18-1-31.
 *
 * @author: darrenfu
 * @date: 18-1-31
 */
public class Loader {
    private static final Logger log = LoggerFactory.getLogger(Loader.class);

    protected static String dir = "/home/darrenfu/IdeaProjects/pampas/pampas-grpc/df/open/grpc/hello";

    private static DynamicMultiClassLoader loader = DynamicMultiClassLoader.getLoader();


    public static DynamicMultiClassLoader getLoader() {
        return loader;
    }

    public static Class load(String className) throws ClassNotFoundException {
        return loader.loadClass(className);
    }


    public static URL toUrl(String path) {
        File classFile = new File(path);
        try {
            return classFile.toURI().toURL();
        } catch (MalformedURLException e) {
            log.error("文件路径错误:{}", path, e);
            throw new RuntimeException("文件路径错误:" + path + ",Error:" + e.getMessage());
        }
    }


}
