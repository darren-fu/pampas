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

package com.github.pampas.common.exception;

import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Created by darrenfu on 18-2-1.
 *
 * @author: darrenfu
 * @date: 18-2-1
 */
public class PampasException extends RuntimeException {

    private HttpResponseStatus status;

    private String contentType;

    public PampasException(Throwable ex) {
        this(HttpResponseStatus.BAD_GATEWAY, HttpHeaderValues.APPLICATION_JSON.toString(), ex.getMessage(), ex);
    }

    public PampasException(String message) {
        this(HttpResponseStatus.BAD_GATEWAY, message);
    }

    public PampasException(HttpResponseStatus status, String message) {
        this(status, HttpHeaderValues.APPLICATION_JSON.toString(), message);
    }

    public PampasException(String message, Throwable ex) {
        this(HttpResponseStatus.BAD_GATEWAY, HttpHeaderValues.APPLICATION_JSON.toString(), message, ex);
    }

    public PampasException(HttpResponseStatus status, String contentType, String message) {
        super(message);
        this.status = status;
        this.contentType = contentType;
    }

    public PampasException(HttpResponseStatus status, String contentType, String message, Throwable ex) {
        super(message, ex);
        this.status = status;
        this.contentType = contentType;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public String getContentType() {
        return contentType;
    }
}
