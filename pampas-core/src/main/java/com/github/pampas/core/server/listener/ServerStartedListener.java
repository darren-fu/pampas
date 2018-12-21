package com.github.pampas.core.server.listener;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;
import com.github.pampas.core.server.PampasServer;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
@Spi(scope = Scope.SINGLETON, desc = "网关启动完成监听器")
public interface ServerStartedListener {
    void started(PampasServer pampasServer);
}
