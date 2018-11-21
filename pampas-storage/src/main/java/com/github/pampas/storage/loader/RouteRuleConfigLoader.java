package com.github.pampas.storage.loader;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.core.route.RouteRuleConfig;

/**
 * Description:加载路由配置
 * User: darrenfu
 * Date: 2018-11-16
 */
@SpiMeta(name = "loader-route-rule-mysql", key = PampasConsts.ConfigLoaderKey.ROUTE_RULE)
public class RouteRuleConfigLoader extends AbstractConfigLoader<RouteRuleConfig> {

    @Override
    public Class<RouteRuleConfig> configClass() {
        return RouteRuleConfig.class;
    }

    @Override
    public RouteRuleConfig doConfigLoad() {
        log.info("开始加载路由规则:{}", RouteRuleConfigLoader.class.getSimpleName());

        log.info("完成加载路由规则:{}", RouteRuleConfigLoader.class.getSimpleName());
        return null;
    }


}
