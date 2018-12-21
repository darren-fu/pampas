package com.github.pampas.core.server.listener;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;
import com.github.pampas.core.server.PampasServer;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-20
 */
@Spi(scope = Scope.SINGLETON, desc = "网关准备启动监听器")
public interface ServerReadyToStartListener {
    /**
     * Ready.
     *
     * @param pampasServer the pampas server
     */
    void ready(PampasServer pampasServer);
}
