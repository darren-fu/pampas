package com.github.pampas.core.server.listener;

import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;
import com.github.pampas.core.server.PampasServer;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
@Spi(scope = Scope.SINGLETON,desc = "网关配置加载完成时监听器")
public interface ServerConfigLoadedListener {

    /**
     * Config loaded.
     *
     * @param pampasServer     the pampas server
     * @param loadedConfigList the loaded config list
     */
    void configLoaded(PampasServer pampasServer, List<VersionConfig> loadedConfigList);

}
