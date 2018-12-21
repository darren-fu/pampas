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


import lombok.Data;

/**
 * Created by darrenfu on 18-1-29.
 *
 * @author: darrenfu
 * @date: 18-1-29
 */
public interface PampasResponse<T> extends Operation {
    PampasResponse<String> OK_RESP = new OKPampasResponse();

    long id();

    boolean success();

    T responseData();

    Throwable exception();

    @Data
    class OKPampasResponse implements PampasResponse<String> {
        @Override
        public long id() {
            return 0;
        }

        @Override
        public boolean success() {
            return true;
        }

        @Override
        public String responseData() {
            return "OK";
        }

        @Override
        public Throwable exception() {
            return null;
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
            return "[" + OKPampasResponse.class.getSimpleName() + "]" + this.responseData();
        }
    }


    @Data
    class SuccessPampasResponse implements PampasResponse<Object> {
        private Object responseData;

        public SuccessPampasResponse(Object responseData) {
            this.responseData = responseData;
        }

        @Override
        public long id() {
            return 0;
        }

        @Override
        public boolean success() {
            return true;
        }

        @Override
        public Object responseData() {
            return responseData;
        }

        @Override
        public Throwable exception() {
            return null;
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
            return "[" + OKPampasResponse.class.getSimpleName() + "]" + this.responseData();
        }
    }

    class ExceptionPampasResponse implements PampasResponse<Throwable> {


        private Throwable throwable;

        public ExceptionPampasResponse(Throwable throwable) {
            this.throwable = throwable;
        }


        @Override
        public long id() {
            return 0;
        }

        @Override
        public boolean success() {
            return false;
        }

        @Override
        public Throwable responseData() {
            return throwable;
        }

        @Override
        public Throwable exception() {
            return null;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }
    }

}
