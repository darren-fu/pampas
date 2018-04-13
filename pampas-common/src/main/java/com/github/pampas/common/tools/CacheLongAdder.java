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

import java.io.Serializable;
import java.util.concurrent.atomic.LongAdder;

/**
 * 缓存Long值的LongAdder
 * Created by darrenfu on 17-7-24.
 */
public class CacheLongAdder extends LongAdder implements Serializable {

    private static final long serialVersionUID = 7249069246863181097L;

    /**
     * 上次写时间
     */
    private volatile Long lastModify = null;
    /**
     * 上次读时间
     */
    private volatile Long lastRead = null;

    /**
     * 缓存的long值
     */
    private Long cacheValue = null;

    public CacheLongAdder() {
        this.lastModify = System.currentTimeMillis();
    }

    @Override
    public void add(long x) {
        super.add(x);
        // 不需要对此值的变更进行并发控制，判断缓存值失效只需要比较大小即可
        lastModify = System.currentTimeMillis();
    }

    @Override
    public long sum() {
        if (cacheValue == null || lastRead == null || lastRead < lastModify) {
            cacheValue = super.sum();
            // 在没有写操作的情况下，直接使用缓存long值即可
            // 只有在缓存失效，即发生了新的写操作的情况下，才需要更新lastRead的时间，避免下次读操作的判断错误
            // 对并发情况下可能存在的误差是可以容忍度的，因为LongAdder本身的sum方法就不是原子性的
            lastRead = System.currentTimeMillis();
        }
        return cacheValue;
    }
}
