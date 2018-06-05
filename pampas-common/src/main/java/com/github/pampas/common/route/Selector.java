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

package com.github.pampas.common.route;

import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;

/**
 * API路由选择器
 * RequestURI + Header --#Selector#--> Locator
 * Created by darrenfu on 18-2-10.
 *
 * @author: darrenfu
 * @date: 18-2-10
 */
@Spi(scope = Scope.SINGLETON)
public interface Selector<R extends PampasRequest> {

    String name();

//    boolean isMatch(R request);

    Locator select(R request);
}
