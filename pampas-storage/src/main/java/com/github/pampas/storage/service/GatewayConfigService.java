package com.github.pampas.storage.service;

import com.github.pampas.common.config.DefinableConfig;
import com.github.pampas.storage.entity.GatewayConfig;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-06
 */
public interface GatewayConfigService {

    void save(String group, String gatewayInstanceId, String spiInterface, String spiClass, String spiName, String spiDesc, List<DefinableConfig.PropDefine> configList);


    List<GatewayConfig> getGatewayConfig(String group, String gatewayInstanceId, String spiClass);
}
