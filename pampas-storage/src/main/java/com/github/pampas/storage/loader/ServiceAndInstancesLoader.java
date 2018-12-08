package com.github.pampas.storage.loader;

import com.fasterxml.jackson.databind.JavaType;
import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.discover.ServiceAndInstancesConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.rule.AbstractRule;
import com.github.pampas.common.route.rule.HostStrategyEnum;
import com.github.pampas.common.route.rule.RulePackage;
import com.github.pampas.common.tools.JsonTools;
import com.github.pampas.common.tools.StreamTools;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.core.route.RouteRuleConfig;
import com.github.pampas.storage.config.SpringContextHolder;
import com.github.pampas.storage.entity.ServiceInstance;
import com.github.pampas.storage.service.ServiceInstanceService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
@SpiMeta(name = ServiceAndInstancesLoader.SPI_META_NAME, key = PampasConsts.ConfigLoaderKey.SERVER_CONTEXT, order = 100)
public class ServiceAndInstancesLoader extends AbstractConfigLoader<ServiceAndInstancesConfig> {

    public static final String SPI_META_NAME = "loader-service-instance";

    @Override
    public Class<ServiceAndInstancesConfig> configClass() {
        return ServiceAndInstancesConfig.class;
    }

    @Override
    public ServiceAndInstancesConfig doConfigLoad() {
        log.info("加载服务实例列表:{}", ServiceAndInstancesLoader.class.getSimpleName());

        Set<String> serviceNameSet = new HashSet<>(64);
        RouteRuleConfig routeRuleConfig = (RouteRuleConfig) SpiContext.getContext(VersionConfig.class)
                .getSpiInstanceByName(RouteRuleConfig.SPI_META_NAME);
        for (RulePackage rulePackage : routeRuleConfig.getRulePackages()) {
            if (CollectionUtils.isEmpty(rulePackage.getRuleList())) {
                continue;
            }
            for (AbstractRule rule : rulePackage.getRuleList()) {
                if (rule.getHostStrategy() == HostStrategyEnum.AUTO) {
                    serviceNameSet.add(rule.getService());
                }
            }
        }
        log.info("网关需要获取实例列表的服务:[{}]个,如下:{}", serviceNameSet.size(), serviceNameSet);


        ServiceAndInstancesConfig serviceAndInstancesConfig = (ServiceAndInstancesConfig) SpiContext.getContext(VersionConfig.class).getSpiInstanceByName(ServiceAndInstancesConfig.SPI_META_NAME);

        // 获取所有服务实例
        if (serviceNameSet != null && serviceNameSet.size() > 0) {
            ServiceInstanceService serviceInstanceService = SpringContextHolder.getBean(ServiceInstanceService.class);
            List<ServiceInstance> serviceInstanceList = serviceInstanceService.getServiceInstanceList(serviceNameSet.toArray(new String[0]));
            Map<String, List<ServiceInstance>> instanceListMap = StreamTools.groupBy(serviceInstanceList, ServiceInstance::getServiceName);
            for (String serviceName : serviceNameSet) {
                List<ServiceInstance> instanceList = instanceListMap.getOrDefault(serviceName, Collections.EMPTY_LIST);
                List<ServerInstance> serverInstanceList = instanceList.stream().map(v -> convert(v)).filter(v -> v != null).collect(Collectors.toList());
                serviceAndInstancesConfig.addServiceAndInstance(serviceName, serverInstanceList);
                log.info("发现服务[{}]，实例列表[{}]个:{}", serviceName, serverInstanceList.size(), serverInstanceList);
            }
        }

        return serviceAndInstancesConfig;
    }


    private ServerInstance convert(ServiceInstance instance) {
        try {
            ServerInstance si = new ServerInstance(instance.getServiceName());
            si.setHost(instance.getHost());
            si.setPort(instance.getPort());
            si.setProtocol(instance.getProtocol());
            si.setVersion(instance.getVersion());
            si.setRoom(instance.getRoom());
            si.setStartTimestamp(instance.getStartTime().getTime());
            si.setInstanceId(instance.getInstanceId());
            si.setWarmupSeconds(instance.getWarmupSeconds());
            si.setWeight(instance.getWeight());
            si.setGroup(instance.getGroup());
            si.setIsAlive(instance.getStatus() == 1);

            if (StringUtils.isNotEmpty(instance.getProps())) {
                Map<String, String> propMap = new HashMap<>();
                JavaType listType = JsonTools.NON_NULL.buildCollectionType(List.class, Map.class);
                List<Map<String, String>> propMapList = JsonTools.NON_NULL.fromJson(instance.getProps(), listType);

                for (Map<String, String> map : propMapList) {
                    propMap.put(map.get("key"), map.get("value"));
                }
                si.setProps(propMap);
            }
            si.ready();
            return si;
        } catch (Exception ex) {
            log.error("转换服务实例数据失败:{},instance:{}", ex.getMessage(), instance, ex);
            return null;
        }

    }

}
