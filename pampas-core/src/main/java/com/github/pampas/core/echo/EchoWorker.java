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

package com.github.pampas.core.echo;

import com.github.df.pampas.common.exec.AbstractWorker;
import com.github.df.pampas.common.exec.payload.RequestInfo;
import com.github.df.pampas.common.exec.payload.ResponseInfo;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.concurrent.CompletableFuture;

/**
 * 最简单的worker
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
public class EchoWorker extends AbstractWorker<FullHttpRequest, String> {

    @Override
    protected void doAfter(String name) {
        System.out.println("do_after:" + Thread.currentThread().getName());
    }

    @Override
    public CompletableFuture<ResponseInfo<String>> doExecute(RequestInfo<FullHttpRequest> req) {
        return null;
    }


}
