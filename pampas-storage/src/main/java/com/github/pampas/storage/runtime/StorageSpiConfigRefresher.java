package com.github.pampas.storage.runtime;

import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.extension.advance.SpiConfig;
import com.github.pampas.common.extension.advance.SpiConfigRefresher;
import com.github.pampas.common.loadbalance.LoadBalancer;
import com.github.pampas.common.tools.StreamTools;
import com.github.pampas.core.base.PampasContext;
import com.github.pampas.core.server.GatewayServer;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.GatewaySpi;
import com.github.pampas.storage.service.GatewaySpiService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-20
 */
@SpiMeta(name = "storage-spi-config-refresher")
public class StorageSpiConfigRefresher implements SpiConfigRefresher {

    private static final Logger log = LoggerFactory.getLogger(StorageSpiConfigRefresher.class);

    @Override
    public void refreshSpiConfig() {

        SpiContext<LoadBalancer> context = SpiContext.getContext(LoadBalancer.class);
        List<LoadBalancer> spiInstances = context.getSpiInstances();
        spiInstances.forEach(v-> System.out.println(v.getClass() + ":" + SpiContext.getSpiMeta(v.getClass())));


        GatewaySpiService gatewaySpiService = SpringContextHolder.getBean(GatewaySpiService.class);
        GatewayServer currentServer = PampasContext.getCurrentServer();
        List<GatewaySpi> spiListInGateway = gatewaySpiService.getSpiListInGateway(currentServer.group(), currentServer.id());
        if (CollectionUtils.isEmpty(spiListInGateway)) {
            return;
        }
        Map<String, List<GatewaySpi>> spiMap = StreamTools.groupBy(spiListInGateway, GatewaySpi::getSpiInterface);

        for (Map.Entry<String, List<GatewaySpi>> spiListEntry : spiMap.entrySet()) {
            String spiInterface = spiListEntry.getKey();
            try {
                Class<?> clz = Class.forName(spiInterface);
                List<GatewaySpi> spiList = spiListEntry.getValue();

                SpiContext<?> spiContext = SpiContext.getContext(clz);
                List<SpiConfig> spiConfigList = spiList.stream().map(v -> {
                    SpiConfig spiConfig = new SpiConfig();
                    spiConfig.setSpiName(v.getSpiName());
                    spiConfig.setOrder(v.getOrder());
                    spiConfig.setActive(v.getStatus());
                    return spiConfig;
                }).collect(Collectors.toList());
                spiContext.updateSpiConfig(spiConfigList);
            } catch (ClassNotFoundException e) {
                log.error("没有发现SPI:{}", spiInterface);
            }
        }

        List<LoadBalancer> spiInstances2 = context.getSpiInstances();
        spiInstances2.forEach(v-> System.out.println(v.getClass() + ":" + SpiContext.getSpiMeta(v.getClass())));

    }
}
