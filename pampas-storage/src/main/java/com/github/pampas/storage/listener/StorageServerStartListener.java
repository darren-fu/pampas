package com.github.pampas.storage.listener;

import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.server.PampasServer;
import com.github.pampas.core.server.listener.ServerReadyToStartListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
@SpiMeta
public class StorageServerStartListener implements ServerReadyToStartListener {

    private static final Logger log = LoggerFactory.getLogger(StorageServerStartListener.class);

    @Override
    public void readyToStart(PampasServer pampasServer) {
        log.info("服务器{}准备启动:{}", pampasServer, LocalDateTime.now().toString());
    }
}
