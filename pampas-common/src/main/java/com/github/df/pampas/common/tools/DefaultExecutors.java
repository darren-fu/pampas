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

import io.netty.util.NettyRuntime;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * 默认并发执行器，都返回CompletableFuture
 *
 * @author: darrenfu
 * @date: 18 -1-29
 */
@SuppressWarnings("unused")
public class DefaultExecutors {

    /**
     * The Default executor.
     */
    static final ThreadPoolExecutor DEFAULT_EXECUTOR;
    private static final int DEFAULT_CORE_THREADS;

    static {
        DEFAULT_CORE_THREADS = Math.max(1, NettyRuntime.availableProcessors() * 2);

        DEFAULT_EXECUTOR = new ThreadPoolExecutor(DEFAULT_CORE_THREADS, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    /**
     * Submit Callable
     *
     * @param <T>      the type parameter
     * @param callable the callable
     * @return the completable future
     */
    public static <T> CompletableFuture<T> submit(Callable<? extends T> callable) {
        CompletableFuture<T> future = new CompletableFuture<>();
        DEFAULT_EXECUTOR.execute(() -> {
            try {
                future.complete(callable.call());
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }


    /**
     * Submit Runnable
     *
     * @param runnable the runnable
     * @return the completable future
     */
    public static CompletableFuture<Void> submit(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, DEFAULT_EXECUTOR);
    }

    /**
     * Submit Supplier
     *
     * @param <T>      the type parameter
     * @param supplier the supplier
     * @return the completable future
     */
    public static <T> CompletableFuture<T> submit(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, DEFAULT_EXECUTOR);
    }

}
