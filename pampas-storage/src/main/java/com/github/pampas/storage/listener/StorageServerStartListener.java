package com.github.pampas.storage.listener;

import com.github.pampas.common.config.DefinableConfig;
import com.github.pampas.common.exec.Worker;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.server.PampasServer;
import com.github.pampas.core.server.listener.ServerReadyToStartListener;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.GatewayInstance;
import com.github.pampas.storage.entity.GatewaySpi;
import com.github.pampas.storage.service.GatewayConfigService;
import com.github.pampas.storage.service.GatewayInstanceService;
import com.github.pampas.storage.service.GatewaySpiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

        CompletableFuture.runAsync(() -> {
            SpiContext<DefinableConfig> listableConfigContext = SpiContext.getContext(DefinableConfig.class);
            List<DefinableConfig> spiInstances = listableConfigContext.getSpiInstances();
            log.info("网关可配置Config列表:{}", spiInstances);
            GatewayConfigService gatewayConfigService = SpringContextHolder.getBean(GatewayConfigService.class);

            for (DefinableConfig definableConfig : spiInstances) {
                List<DefinableConfig.PropDefine> propDefines = definableConfig.propDefines();
                gatewayConfigService.save(pampasServer.group(), pampasServer.id(),
                        DefinableConfig.class.getName(), definableConfig.getClass().getName(),
                        SpiContext.getSpiName(definableConfig.getClass()), SpiContext.getSpiDesc(definableConfig.getClass()),
                        propDefines);
            }

            List<Class<Worker>> workerClassList = SpiContext.getContext(Worker.class).getSpiClasses(null);
            log.info("网关SPI列表-<Worker>: {}", workerClassList);
            GatewaySpiService spiService = SpringContextHolder.getBean(GatewaySpiService.class);
            List<GatewaySpi> spiList = workerClassList.stream().map(v -> {
                GatewaySpi spi = new GatewaySpi();
                spi.setGatewayGroup(pampasServer.group());
                spi.setGatewayInstanceId(pampasServer.id());
                spi.setSpiInterface(Worker.class.getName());
                spi.setSpiClass(v.getName());
                spi.setSpiName(SpiContext.getSpiName(v));
                spi.setSpiDesc(SpiContext.getSpiDesc(v));
                return spi;
            }).collect(Collectors.toList());
            spiService.saveGatewaySpi(pampasServer.group(), pampasServer.id(), spiList);
            log.info("保存网关SPI列表-<Worker>: {}个", spiList.size());

        });


    }
}
