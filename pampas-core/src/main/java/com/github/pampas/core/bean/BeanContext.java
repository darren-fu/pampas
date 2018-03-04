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

package com.github.pampas.core.bean;

import com.github.df.pampas.common.config.VersionConfig;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darrenfu on 18-3-4.
 *
 * @author: darrenfu
 * @date: 18-3-4
 */
public class BeanContext {

    public static void main(String[] args) throws InterruptedException {
        Object reference = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue();

        WeakReference<Object> weakReference = new WeakReference<Object>(reference, referenceQueue);

        System.out.println("weakReference.isEnqueued():" + weakReference.isEnqueued());

        System.out.println("weakReference.get():" + weakReference.get());
        System.out.println("reference:" + reference);
        reference = null;
        System.out.println("weakReference.isEnqueued():" + weakReference.isEnqueued());
        System.gc();

        Thread.sleep(1000L);
        System.out.println("weakReference.isEnqueued():" + weakReference.isEnqueued());

        System.out.println(reference);
        System.out.println(weakReference.get());
        System.out.println("weakReference.isEnqueued():" + weakReference.isEnqueued());


        List<String> list = new ArrayList<>();




    }


}
