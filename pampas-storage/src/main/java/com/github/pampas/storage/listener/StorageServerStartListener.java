package com.github.pampas.storage.listener;

import com.github.pampas.common.config.ListableConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.server.PampasServer;
import com.github.pampas.core.server.listener.ServerReadyToStartListener;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.GatewayInstance;
import com.github.pampas.storage.service.GatewayConfigService;
import com.github.pampas.storage.service.GatewayInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

        GatewayInstanceService gatewayInstanceService = SpringContextHolder.getBean(GatewayInstanceService.class);

        GatewayInstance instance = new GatewayInstance();
        instance.setInstanceId(pampasServer.id());
        instance.setServerName(pampasServer.serverName());
        instance.setHost(pampasServer.address().getHostAddress());
        instance.setHostName(pampasServer.address().getHostName());
        instance.setVersion(pampasServer.version());
        instance.setProxyPort(pampasServer.port());
        instance.setAdminPort(pampasServer.port());
        instance.setStartTime(new Date(pampasServer.startTimestamp()));
        gatewayInstanceService.save(instance);
        log.info("记录网关服务启动: {}", instance);


        SpiContext<ListableConfig> listableConfigContext = SpiContext.getContext(ListableConfig.class);
        List<ListableConfig> spiInstances = listableConfigContext.getSpiInstances();
        log.info("网关可配置Config列表:{}", spiInstances);
        GatewayConfigService gatewayConfigService = SpringContextHolder.getBean(GatewayConfigService.class);

        for (ListableConfig listableConfig : spiInstances) {
            List<ListableConfig.PropDefine> propDefines = listableConfig.propDefines();
            gatewayConfigService.save(pampasServer.group(), pampasServer.id(), listableConfig.getClass().getSimpleName(), propDefines);
        }


    }
}
