package com.github.pampas.storage.listener;

import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.server.PampasServer;
import com.github.pampas.core.server.listener.ServerConfigLoadedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
@SpiMeta(name = "storage-server-config-loaded-listener")
public class StorageServerConfigLoadedListener implements ServerConfigLoadedListener {

    private static final Logger log = LoggerFactory.getLogger(StorageServerConfigLoadedListener.class);

    @Override
    public void configLoaded(PampasServer pampasServer, List<VersionConfig> loadedConfigList) {


    }
}
