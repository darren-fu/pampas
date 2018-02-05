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

package com.github.df.pampas.common.exec.payload;


/**
 * Created by darrenfu on 18-1-29.
 *
 * @author: darrenfu
 * @date: 18-1-29
 */
public interface ResponseInfo<T> {
    ResponseInfo<String> OK_RESP = new OKResponseInfo();

    long id();

    boolean success();

    T responseData();

    Throwable exception();


    class OKResponseInfo implements ResponseInfo<String> {
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
    }

    class ExceptionResponse implements ResponseInfo<Throwable>{
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
            return null;
        }

        @Override
        public Throwable exception() {
            return null;
        }
    }

}
