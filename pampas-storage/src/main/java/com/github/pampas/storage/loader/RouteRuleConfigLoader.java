package com.github.pampas.storage.loader;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.rule.AbstractRule;
import com.github.pampas.common.route.rule.RulePackage;
import com.github.pampas.common.tools.JsonTools;
import com.github.pampas.common.tools.ext.StringTools;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.core.base.PampasContext;
import com.github.pampas.core.route.RouteRuleConfig;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.RouteRule;
import com.github.pampas.storage.service.RouteRuleService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:加载路由配置
 * User: darrenfu
 * Date: 2018-11-16
 */
@SpiMeta(name = RouteRuleConfigLoader.SPI_META_NAME, key = PampasConsts.ConfigLoaderKey.ROUTE_RULE, order = 0)
public class RouteRuleConfigLoader extends AbstractConfigLoader<RouteRuleConfig> {

    protected static final String SPI_META_NAME = "loader-route-rule-mysql";

    @Override
    public Class<RouteRuleConfig> configClass() {
        return RouteRuleConfig.class;
    }

    @Override
    public RouteRuleConfig doConfigLoad() {
        log.info("开始加载路由规则:{}", RouteRuleConfigLoader.class.getSimpleName());
        RouteRuleService routeRuleService = SpringContextHolder.getBean(RouteRuleService.class);
        List<RouteRule> routeRuleList = routeRuleService.getGatewayRelRouteRule(PampasContext.getCurrentServer().id());
        List<RulePackage> rulePackageList = new ArrayList<>();
        for (RouteRule routeRule : routeRuleList) {
            RulePackage rulePackage = new RulePackage();
            rulePackage.setRuleId(routeRule.getId());
            rulePackage.setName(routeRule.getName());
            rulePackage.setMappingHost(routeRule.getMappingHost());
            String ruleContent = routeRule.getContent();
            if (StringTools.isEmpty(ruleContent)) {
                continue;
            }
            try {
                JavaType listType = JsonTools.NON_NULL.buildCollectionType(List.class, Map.class);
                List<Map> ruleDetailMapList = JsonTools.NON_NULL.fromJson(ruleContent, listType);
                if (CollectionUtils.isNotEmpty(ruleDetailMapList)) {
                    for (Map ruleDetailMap : ruleDetailMapList) {
                        rulePackage.addRule(ruleDetailMap);
                    }
                }
                if (StringTools.isEmpty(ruleDetailMapList)) {
                    continue;
                }
                rulePackageList.add(rulePackage);
            } catch (Exception ex) {
                log.error("解析路由规则失败:{},{}", ex.getMessage(), ex);
            }
        }

        RouteRuleConfig routeRuleConfig = (RouteRuleConfig) SpiContext.getContext(VersionConfig.class).getSpiInstanceByName(RouteRuleConfig.SPI_META_NAME);
        if (rulePackageList.size() > 0) {
            routeRuleConfig.setRulePackages(rulePackageList.toArray(new RulePackage[0]));
            if (log.isDebugEnabled()) {
                int i = 1;
                for (RulePackage rulePackage : rulePackageList) {

                    if (CollectionUtils.isNotEmpty(rulePackage.getRuleList())) {
                        for (AbstractRule rule : rulePackage.getRuleList()) {
                            log.debug("[{}]路由规则[{}]详情:{}", i++, rulePackage.getName(), rule);
                        }
                    }
                }
            }
        }

        log.info("[{}]完成加载路由规则:[{}]个", SPI_META_NAME, routeRuleConfig.getRulePackages().length);

        return routeRuleConfig;
    }


}
