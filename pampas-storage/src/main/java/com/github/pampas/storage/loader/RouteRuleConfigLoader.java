package com.github.pampas.storage.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.rule.RulePackage;
import com.github.pampas.common.tools.JsonTools;
import com.github.pampas.common.tools.ext.StringUtils;
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
@SpiMeta(name = RouteRuleConfigLoader.META_NAME, key = PampasConsts.ConfigLoaderKey.ROUTE_RULE)
public class RouteRuleConfigLoader extends AbstractConfigLoader<RouteRuleConfig> {

    protected static final String META_NAME = "loader-route-rule-mysql";

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
        JsonTools jsonTools = JsonTools.nonNullMapper();
        for (RouteRule routeRule : routeRuleList) {
            RulePackage rulePackage = new RulePackage();
            rulePackage.setRuleId(routeRule.getId());
            rulePackage.setName(routeRule.getName());
            rulePackage.setMappingHost(routeRule.getMappingHost());
            String ruleContent = routeRule.getContent();
            if (StringUtils.isEmpty(ruleContent)) {
                continue;
            }
            try {
                List<Map> ruleDetailMapList = jsonTools.nonNullMapper().fromJson(ruleContent, new TypeReference<List<Map>>() {
                });
                if (CollectionUtils.isEmpty(ruleDetailMapList)) {
                    for (Map ruleDetailMap : ruleDetailMapList) {
                        rulePackage.addRule(ruleDetailMap);
                    }
                }
                if (StringUtils.isEmpty(ruleDetailMapList)) {
                    continue;
                }
                rulePackageList.add(rulePackage);
            } catch (Exception ex) {
                log.error("解析路由规则失败:{},{}", ex.getMessage(), ex);
            }
        }

        RouteRuleConfig routeRuleConfig = new RouteRuleConfig();
        if (rulePackageList.size() > 0) {
            routeRuleConfig.setRulePackages(rulePackageList.toArray(new RulePackage[0]));
        }

        log.info("完成加载路由规则[{}]:{}", META_NAME, routeRuleConfig);
        return routeRuleConfig;
    }


}
