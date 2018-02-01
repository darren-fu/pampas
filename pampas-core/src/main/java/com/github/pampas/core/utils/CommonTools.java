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

package com.github.pampas.core.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * 通用工具
 * Created by darrenfu on 17-7-19.
 */
public class CommonTools {

    /**
     * Empty to null string.
     *
     * @param string the string
     * @return the string
     */
    public static String emptyToNull(String string) {
        return string == null || string.isEmpty() ? null : string;
    }

    /**
     * Is empty boolean.
     *
     * @param coll the coll
     * @return the boolean
     */
    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * Is empty boolean.
     *
     * @param coll the coll
     * @return the boolean
     */
    public static boolean isNotEmpty(Collection coll) {
        return coll != null && coll.size() > 0;
    }

    /**
     * Is empty boolean.
     *
     * @param map the map
     * @return the boolean
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }


    /**
     * Is not empty boolean.
     *
     * @param map the map
     * @return the boolean
     */
    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    /**
     * 将版本号转换成 BigDecimal
     * 1.2.3->new BigDecimal("1.23")
     *
     * @param versionNumber the version number
     * @return the big decimal
     */
    public static BigDecimal convertVersionNum(String versionNumber) {
        StringBuffer sb = new StringBuffer(16);
        boolean alreadyAppendDot = false;
        for (char c : versionNumber.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
            if (!alreadyAppendDot && c == '.') {
                sb.append(c);
                alreadyAppendDot = true;
            }
        }

        return new BigDecimal(sb.toString());

    }

}
