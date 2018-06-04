package com.github.pampas.core.route;

import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.route.Selector;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Description: 请求选择器
 * 基于Rule将request路由到不同service及接口
 * User: darrenfu
 * Date: 2018-06-04
 */
@SpiMeta(name = PampasRequestSelector.SELECTOR_NAME, order = 80)
public class PampasRequestSelector implements Selector<PampasRequest<FullHttpRequest>>, Configurable<RouteRuleConfig> {

    protected static final String SELECTOR_NAME = "http-request-selector";

    private RouteRuleConfig config;


    public PampasRequestSelector() {

    }


    @Override
    public RouteRuleConfig getConfig() {
        return this.config;
    }

    @Override
    public Class<RouteRuleConfig> configClass() {
        return RouteRuleConfig.class;
    }

    @Override
    public Configurable setupWithConfig(RouteRuleConfig routeRuleConfig) {
        this.config = routeRuleConfig;
        return this;
    }

    @Override
    public String name() {
        return SELECTOR_NAME;
    }

    @Override
    public boolean isMatch(PampasRequest<FullHttpRequest> request) {
        return false;
    }

    @Override
    public Locator select(PampasRequest<FullHttpRequest> request) {
        return null;
    }
}
