package com.github.pampas.storage.loader;

import com.github.pampas.common.config.ListableConfig;
import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.core.base.PampasContext;
import com.github.pampas.core.filter.AuthFilterConfig;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.GatewayConfig;
import com.github.pampas.storage.service.GatewayConfigService;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-06
 */
@SpiMeta(name = AuthFilterConfigLoader.SPI_META_NAME, order = 0)
public class AuthFilterConfigLoader extends AbstractConfigLoader<AuthFilterConfig> {

    public static final String SPI_META_NAME = "config-loader-auth-filter";


    @Override
    public AuthFilterConfig doConfigLoad() {
        GatewayConfigService gatewayConfigService = SpringContextHolder.getBean(GatewayConfigService.class);
        List<GatewayConfig> gatewayConfigList = gatewayConfigService.getGatewayConfig(PampasContext.getCurrentServer().group(),
                PampasContext.getCurrentServer().id(), AuthFilterConfig.class.getSimpleName());
        AuthFilterConfig config = (AuthFilterConfig) SpiContext.getContext(ListableConfig.class).getSpiInstanceByName(AuthFilterConfig.SPI_META_NAME);
        ConcurrentHashMap<String, String> propMap = config.getPropMap();
        for (GatewayConfig gatewayConfig : gatewayConfigList) {
            propMap.put(gatewayConfig.getKey(), gatewayConfig.getValue());
        }
        log.info("加载配置[{}]完成:{}", AuthFilterConfig.class.getSimpleName(), config);
        return config;
    }

    @Override
    public Class<AuthFilterConfig> configClass() {
        return AuthFilterConfig.class;
    }
}
