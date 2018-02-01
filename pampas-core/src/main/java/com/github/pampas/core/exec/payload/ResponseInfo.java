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

package com.github.pampas.core.exec.payload;

import org.asynchttpclient.Response;

/**
 * Created by darrenfu on 18-1-29.
 *
 * @author: darrenfu
 * @date: 18-1-29
 */
public interface ResponseInfo<T> {


    long getId();

    T getResponseData();


    class DefaultResponseInfo<Response> implements ResponseInfo<Response>{

        @Override
        public long getId() {
            return 100L;
        }

        @Override
        public Response getResponseData() {
            return null;
        }
    }


    class DefaultService{
        DefaultResponseInfo<Response> getResponse(){
            return new DefaultResponseInfo();
        }
    }

    public static void main(String[] args) {
        DefaultService service = new DefaultService();
        DefaultResponseInfo<Response> responseInfo = service.getResponse();
        Response responseData = responseInfo.getResponseData();
    }

}
