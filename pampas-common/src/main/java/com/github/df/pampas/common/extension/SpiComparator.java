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

package com.github.df.pampas.common.extension;

import java.util.Comparator;

/**
 * Priority comparator
 */

public class SpiComparator<T> implements Comparator<T> {

    /**
     * order 大的排在后面,如果没有设置sequence的排到最前面
     */
    @Override
    public int compare(T o1, T o2) {
        SpiCondition p1 = o1.getClass().getAnnotation(SpiCondition.class);
        SpiCondition p2 = o2.getClass().getAnnotation(SpiCondition.class);
        if (p1 == null) {
            return 1;
        } else if (p2 == null) {
            return -1;
        } else {
            return p1.order() - p2.order();
        }
    }


}
