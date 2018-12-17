package com.github.pampas.storage.loader;

import com.github.pampas.common.config.DefinableConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.filter.AuthFilterConfig;
import com.github.pampas.storage.entity.GatewayConfig;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-06
 */
@SpiMeta(name = AuthFilterConfigLoader.SPI_META_NAME, order = 0)
public class AuthFilterConfigLoader extends AbstractDefinableConfigLoader<AuthFilterConfig> {

    public static final String SPI_META_NAME = "config-loader-auth-filter";


    @Override
    public AuthFilterConfig doConfigLoad() {
        List<GatewayConfig> gatewayConfigList = super.loadGatewayConfig();
        AuthFilterConfig config = (AuthFilterConfig) SpiContext.getContext(DefinableConfig.class).getSpiInstanceByName(AuthFilterConfig.SPI_META_NAME);
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
