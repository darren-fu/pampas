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

package com.github.df.pampas.common.tools;


import java.util.*;

/**
 * Created by darrenfu on 18-1-24.
 *
 * @author: darrenfu
 * @date: 18-1-24
 */
public class CollectionTools {


    public static <K, V> Map<K, V> toMap(K key, V val) {
        Map<K, V> map = new HashMap();
        map.put(key, val);
        return map;
    }

    public static <K> List<K> toList(K... val) {
        if (val == null || val.length == 0) {
            return new ArrayList<>();
        }
        List<K> list = new ArrayList<>(val.length);
        for (K k : val) {
            list.add(k);
        }

        return list;
    }

}
