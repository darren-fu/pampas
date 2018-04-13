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

package com.github.pampas.discover.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.github.pampas.common.tools.CommonTools;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;

/**
 * Created by darrenfu on 18-2-7.
 *
 * @author: darrenfu
 * @date: 18-2-7
 */
public class ConsulSupport {

    public static ConsulClient getClient() {
        String property = System.getProperty("pampas.consul.originUri");
        if (StringUtils.isNotBlank(property)) {
            URI uri = CommonTools.toURI(property);


            return new ConsulClient(uri.getScheme() + "://" + uri.getHost(), uri.getPort());
        }
        return new ConsulClient();
    }
}
