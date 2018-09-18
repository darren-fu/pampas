package com.github.pampas.storage.listener;

import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.tools.TaskTools;
import com.github.pampas.core.server.PampasServer;
import com.github.pampas.core.server.listener.ServerStartedListener;
import com.github.pampas.storage.dao.GatewayServerDao;
import com.github.pampas.storage.entity.DBGatewayServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-18
 */
@SpiMeta
public class StorageServerStartedListener implements ServerStartedListener {

    private static final Logger log = LoggerFactory.getLogger(StorageServerStartListener.class);

    @Override
    public void started(PampasServer pampasServer) {
        log.info("服务器{}启动完成:{}", pampasServer, LocalDateTime.now().toString());
        TaskTools.scheduledTask("gateway-refresh-timer",30_000L,()->{
            GatewayServerDao gatewayServerDao = new GatewayServerDao();
            DBGatewayServer dbGatewayServer = new DBGatewayServer();
            dbGatewayServer.setServerId(pampasServer.id());
            dbGatewayServer.setServerName(pampasServer.serverName());
            dbGatewayServer.setPort(pampasServer.port());
            dbGatewayServer.setStart(new Date(pampasServer.startTimestamp()));
            dbGatewayServer.setAddress(pampasServer.address().getHostAddress());
            gatewayServerDao.updateGatewayServerStat(dbGatewayServer);
            return null;
        });
    }
}
