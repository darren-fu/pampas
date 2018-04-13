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

package com.github.pampas.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class工具类
 * Created by darrenfu on 17-7-23.
 */
@SuppressWarnings("unused")
public class ClassTools {
    private static final Logger log = LoggerFactory.getLogger(ClassTools.class);

    /**
     * Cast to t.
     *
     * @param <T> the type parameter
     * @param obj the obj
     * @param clz the clz
     * @return the t
     */
    public static <T> T castTo(Object obj, Class<T> clz) {
        return clz.cast(obj);
    }

    /**
     * Instance t.
     *
     * @param <T>       the type parameter
     * @param targetClz the target clz
     * @return the t
     */
    public static <T> T instance(Class<T> targetClz) {
        try {
            return targetClz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.warn("Instantiation failed:{}", e.getMessage(), e);
        }
        return null;
    }


    /**
     * Has class boolean.
     *
     * @param clz the clz
     * @return the boolean
     */
    public static boolean hasClass(String clz) {
        try {
            Class.forName(clz);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }


    /**
     * Gets class.
     *
     * @param clz the clz
     * @return the class
     */
    public static Class<?> getClass(String clz) {
        try {
            return Class.forName(clz);
        } catch (ClassNotFoundException e) {
            log.error("no class found:{}", clz, e);
        }
        return null;
    }

}
