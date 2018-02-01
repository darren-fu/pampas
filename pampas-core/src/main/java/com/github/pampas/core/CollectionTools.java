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

package com.github.pampas.core;

import java.util.*;
import java.util.concurrent.*;

import rx.Observable;
import rx.Subscriber;

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
        if (val != null && val.length > 0) {
            return Arrays.asList(val);
        }
        return new ArrayList<>();
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorCompletionService exec = new ExecutorCompletionService(Executors.newCachedThreadPool());

        Future future = exec.submit(() -> {
            System.out.println("Future thread:" + Thread.currentThread().getName());
            Thread.sleep(1_000L);
            if( 130 > 120){
                throw new RuntimeException("find exception....");
            }
            return new Random().nextInt(100);
        });

        Future take = exec.take();

        System.out.println(take.get());
        System.out.println(future.get());

    }


    public static void main1(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(() ->
        {
            System.out.println("Future thread:" + Thread.currentThread().getName());
            Thread.sleep(10000L);
            return new Random().nextInt(100);
        });
        Observable<Integer> observable = Observable.from(future);
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("subscriber thread -> onCompleted:" + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscriber thread -> onError:" + Thread.currentThread().getName());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("subscriber thread -> onNext:" + Thread.currentThread().getName());
            }
        };

        observable.subscribe(subscriber);
        System.out.println("完成");

    }
}
