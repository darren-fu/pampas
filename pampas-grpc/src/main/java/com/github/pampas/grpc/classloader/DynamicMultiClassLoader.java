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

package com.github.pampas.grpc.classloader;

import com.github.pampas.grpc.Loader;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.Manifest;

/**
 * Created by darrenfu on 18-1-31.
 *
 * @author: darrenfu
 * @date: 18-1-31
 */
@SuppressWarnings("Duplicates")
public class DynamicMultiClassLoader extends URLClassLoader implements AutoCloseable {
    private Manifest manifest;
    private ConcurrentHashMap<String, ClassInfo> classInfo = new ConcurrentHashMap<String, ClassInfo>();


    public static DynamicMultiClassLoader getLoader() {
        return ClassloaderInstanceHolder.INSTANCE;
    }

    public static DynamicMultiClassLoader getLoader(URL... urls) {
        DynamicMultiClassLoader instance = ClassloaderInstanceHolder.INSTANCE;
        instance.addURL(urls);
        return instance;
    }

    private static class ClassloaderInstanceHolder {
        private static final DynamicMultiClassLoader INSTANCE = new DynamicMultiClassLoader(new URL[]{Loader.toUrl(System.getProperty("user.dir"))});
    }

    /**
     * Provide delegation constructor
     */
    private DynamicMultiClassLoader(URL[] urls) {
        super(urls);
    }

    /**
     * Same old ClassLoader constructor
     */
    private DynamicMultiClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }


    public DynamicMultiClassLoader addURL(URL... urls) {
        for (URL url : urls) {
            super.addURL(url);
        }
        return this;
    }

    public Class load(String className) throws ClassNotFoundException {
        return this.loadClass(className);
    }

    private String getClassPath(String className) {
        String classPath = className;
        if (className.endsWith(".class")) {
            classPath = className.substring(0, className.length() - 6).replace('.', File.separatorChar) + ".class";
        } else {
            classPath = className.replace('.', File.separatorChar) + ".class";
        }
        for (URL url : super.getURLs()) {
            File dir = new File(url.getPath());
            File[] files = searchFile(dir, classPath);
            if (files != null && files.length > 0) {
                return files[0].getPath();
            }

        }
        return null;
    }


    public File[] searchFile(File folder, final String fileName) {// 递归查找包含关键字的文件

        File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
            @Override
            public boolean accept(File pathname) {// 实现FileFilter类的accept方法
                // 目录或文件包含关键字
                if (pathname.isDirectory()) {
                    return true;
                } else if (pathname.getAbsolutePath().endsWith(fileName)) {
                    System.out.println("#####" + fileName);
                    return true;
                }
                return false;
            }
        });


        List<File> result = new ArrayList<File>();// 声明一个集合
        for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
            if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
                result.add(subFolders[i]);
            } else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                File[] foldResult = searchFile(subFolders[i], fileName);
                for (int j = 0; j < foldResult.length; j++) {// 循环显示文件
                    result.add(foldResult[j]);// 文件保存到集合中
                }
            }
        }

        File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
        result.toArray(files);// 集合数组化
        return files;
    }

    /**
     * This is the method where the task of class loading is delegated to our custom loader.
     *
     * @param name the name of the class
     * @return the resulting <code>Class</code> object
     * @throws ClassNotFoundException if the class could not be found
     */
    @Override
    protected Class findClass(String name) throws ClassNotFoundException {

        try {
            return super.findClass(name);
        } catch (ClassNotFoundException ex) {
            return findClass0(name);
        }
    }


    private Class findClass0(String name) throws ClassNotFoundException {
        // get path of given class
        String classPath = getClassPath(name);
        System.out.println("name:" + name + ":" + classPath);
        if (classPath == null)
            throw new ClassNotFoundException(name);

        // get last modified time of class file
        File f = new File(classPath);
        long lastModified = f.lastModified();

        // check if given class loaded already
        ClassInfo loadedClassInfo = classInfo.get(name);

        // if class loaded is the newest one, no need to reload it
        if ((loadedClassInfo != null) && (loadedClassInfo.lastModified >= lastModified))
            return loadedClassInfo.classType;

        FileInputStream fi = null;

        try {
            fi = new FileInputStream(f);
            byte[] classBytes = new byte[fi.available()];
            fi.read(classBytes);
            Class result = defineClass(name, classBytes, 0, classBytes.length);
            classInfo.put(name, new ClassInfo(result, lastModified));
            return result;
        } catch (Exception e) {
            // We could not find the class, so indicate the problem with an exception
            e.printStackTrace();
            throw new ClassNotFoundException(name);
        } finally {
            if (null != fi) {
                try {
                    fi.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static class ClassInfo {
        public Class classType;

        public long lastModified;

        public ClassInfo(Class classType) {
            this.classType = classType;
            this.lastModified = -1;
        }

        public ClassInfo(Class classType, long lastModified) {
            this.classType = classType;
            this.lastModified = lastModified;
        }
    }
}
