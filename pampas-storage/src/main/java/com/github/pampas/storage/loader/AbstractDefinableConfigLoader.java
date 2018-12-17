package com.github.pampas.storage.loader;

import com.github.pampas.common.config.DefinableConfig;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.core.base.PampasContext;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.GatewayConfig;
import com.github.pampas.storage.service.GatewayConfigService;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-17
 */
public abstract class AbstractDefinableConfigLoader<T extends DefinableConfig> extends AbstractConfigLoader<T> {

    public List<GatewayConfig> loadGatewayConfig(){
        GatewayConfigService gatewayConfigService = SpringContextHolder.getBean(GatewayConfigService.class);
        List<GatewayConfig> gatewayConfigList = gatewayConfigService.getGatewayConfig(PampasContext.getCurrentServer().group(),
                PampasContext.getCurrentServer().id(), configClass().getName());
        return gatewayConfigList;
    }



}
