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

import com.github.pampas.common.exec.AbstractWorker;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.extension.SpiMeta;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.concurrent.CompletableFuture;

/**
 * 最简单的worker
 * Created by darrenfu on 18-2-2.
 *
 * @author: darrenfu
 * @date: 18-2-2
 */
@SpiMeta(name = "echo",order = 100)
public class EchoWorker extends AbstractWorker<FullHttpRequest, String> {

    @Override
    protected void doAfter(String name) {
        System.out.println("do_after:" + Thread.currentThread().getName());
    }

    @Override
    public CompletableFuture<PampasResponse<String>> doExecute(PampasRequest<FullHttpRequest> req) {
        return CompletableFuture.completedFuture(PampasResponse.OK_RESP);
    }


}
