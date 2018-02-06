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

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * Created by darrenfu on 18-2-5.
 *
 * @author: darrenfu
 * @date: 18 -2-5
 */
public class ThreadpoolFactory {

    private static final int PROCESSOR_NUMBER = Runtime.getRuntime().availableProcessors();

    /**
     * Unlimit pool executor.
     *
     * @return the executor
     */
    public static ThreadPoolExecutor unlimitPool() {
        return PoolHolder.unlimitPool;
    }


    private static class PoolHolder {
        /**
         * 无限制的线程池
         */
        public static ThreadPoolExecutor unlimitPool = new ThreadPoolExecutor(PROCESSOR_NUMBER, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());



    }


}
