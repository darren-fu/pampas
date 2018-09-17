package com.github.pampas.core.server.listener;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;
import com.github.pampas.core.server.PampasServer;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
@Spi(scope = Scope.SINGLETON)
public interface ServerReadyToStartListener {
    void readyToStart(PampasServer pampasServer);

}
