package com.github.pampas.storage.loader;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.discover.ServiceAndInstances;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.rule.AbstractRule;
import com.github.pampas.common.route.rule.HostStrategyEnum;
import com.github.pampas.common.route.rule.RulePackage;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.core.route.RouteRuleConfig;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.ServiceInstance;
import com.github.pampas.storage.service.ServiceInstanceService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
@SpiMeta(name = "loader-service-instance", key = PampasConsts.ConfigLoaderKey.SERVER_CONTEXT, order = 100)
public class ServiceAndInstancesLoader extends AbstractConfigLoader<ServiceAndInstances> {

    @Override
    public Class<ServiceAndInstances> configClass() {
        return ServiceAndInstances.class;
    }

    @Override
    public ServiceAndInstances doConfigLoad() {
        log.info("加载服务实例列表:{}", ServiceAndInstancesLoader.class.getSimpleName());

        Set<String> serviceNameSet = new HashSet<>(64);
        RouteRuleConfig routeRuleConfig = (RouteRuleConfig) SpiContext.getContext(VersionConfig.class)
                .getSpiInstanceByName(RouteRuleConfig.SPI_META_NAME);
        for (RulePackage rulePackage : routeRuleConfig.getRulePackages()) {
            for (AbstractRule rule : rulePackage.getRuleList()) {
                if (rule.getHostStrategy() == HostStrategyEnum.AUTO) {
                    serviceNameSet.add(rule.getService());
                }
            }
        }
        log.info("网关需要获取实例列表的服务如下:{}", serviceNameSet);
        ServiceAndInstances serviceAndInstances = new ServiceAndInstances();
        serviceAndInstances.addServiceAndInstance("TestService",
                Arrays.asList(
                        ServerInstance.buildWithUri("TestService", "http://localhost:7003")
                ));
        ServiceInstanceService serviceInstanceService = SpringContextHolder.getBean(ServiceInstanceService.class);
        List<ServiceInstance> serviceInstanceList = serviceInstanceService.getServiceInstanceList(null);

        log.info("完成加载服务实例列表:{}", serviceInstanceList);
        return serviceAndInstances;
    }

}
