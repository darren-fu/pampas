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

/**
 * Request 工具类
 * Created by darrenfu on 18-2-5.
 *
 * @author: darrenfu
 * @date: 18 -2-5
 */
public class RequestTools {


    /**
     * 获取URI中的请求路径
     * uri: /user/get?id=12
     * return
     * \: /user/get
     *
     * @param uri the uri eg. /user/get?id=12
     * @return the path in uri eg. /user/get
     */
    public static String getPathInUri(String uri) {
        int indexOfMark = uri.indexOf("?");
        if (indexOfMark >= 1) {
            return uri.substring(0, indexOfMark);
        }
        return uri;
    }


    /**
     * 过滤请求路径前缀
     * path: /service_a/user/get
     * prefix:/service_a
     * return: /user/get
     *
     * @param path   the path eg./service_a/user/get
     * @param prefix the prefix eg.service_a
     * @return the string /user/get
     */
    public static String stripPrefixInPath(String path, String prefix) {
        return path.replaceFirst(prefix, "");
    }


}
