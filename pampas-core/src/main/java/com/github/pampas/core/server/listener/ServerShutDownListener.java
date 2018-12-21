package com.github.pampas.core.server.listener;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;

/**
 * Description:网关关闭的监听器
 * User: darrenfu
 * Date: 2018-09-17
 */
@Spi(scope = Scope.SINGLETON, desc = "网关关闭的监听器")
public interface ServerShutDownListener {
}
