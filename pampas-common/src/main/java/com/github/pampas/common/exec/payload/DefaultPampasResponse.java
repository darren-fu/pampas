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

package com.github.pampas.common.exec.payload;

import com.google.common.base.MoreObjects;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.Setter;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by darrenfu on 18-2-5.
 *
 * @author: darrenfu
 * @date: 18-2-5
 */
public class DefaultPampasResponse<T> implements PampasResponse<T> {

    @Setter
    private Long id;

    @Setter
    private boolean success = true;

    @Setter
    private T responseData;

    @Setter
    private Throwable exception;

    @Override
    public long id() {
        return id;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public T responseData() {
        return responseData;
    }

    @Override
    public Throwable exception() {
        return exception;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public String toString() {
        String body = null;
        Object status = null;
        if (responseData != null && responseData instanceof FullHttpResponse) {
            FullHttpResponse httpResponse = (FullHttpResponse) this.responseData;
            ByteBuf content = httpResponse.content();
            body = content.toString(UTF_8);
            status = httpResponse.status();
        }

        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("success", success)
                .add("status", status)
                .add("responseData", body == null ? responseData : body)
                .add("exception", exception)
                .toString();
    }
}
