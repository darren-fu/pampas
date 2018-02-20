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

import lombok.Setter;

/**
 * Created by darrenfu on 18-2-5.
 *
 * @author: darrenfu
 * @date: 18-2-5
 */
public class DefaultPampasResponse<T> implements PampasResponse<T> {

    @Setter
    private long id;

    @Setter
    private boolean success;

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
}
