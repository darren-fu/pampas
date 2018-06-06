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

package com.github.pampas.core.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常处理器
 * Created by darrenfu on 18-1-20.
 *
 * @author: darrenfu
 * @date: 18-1-20
 */
@ChannelHandler.Sharable
public class FinalExceptionHandler extends ChannelDuplexHandler {

    private static final Logger log = LoggerFactory.getLogger(FinalExceptionHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        try {
            log.error("处理消息发生异常", cause);
            ctx.close();
        } catch (Throwable ex) {
            log.warn("关闭channel失败:{}", ex);
        }

    }
}
