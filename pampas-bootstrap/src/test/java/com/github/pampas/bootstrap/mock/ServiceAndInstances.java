package com.github.pampas.bootstrap.mock;

import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.discover.ServerInstance;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-13
 */
@Getter
public class ServiceAndInstances implements VersionConfig {

    private Map<String, List<ServerInstance>> serviceMap;

    @Setter
    private Long timestamp;

    public ServiceAndInstances() {
        this.serviceMap = new ConcurrentHashMap<>();
    }


    public ServiceAndInstances addServiceAndInstance(String serviceName, List<ServerInstance> instanceList) {
        serviceMap.put(serviceName, instanceList);
        return this;
    }

    public List<ServerInstance> getInstances(String serviceName) {
        return serviceMap.getOrDefault(serviceName, Collections.EMPTY_LIST);
    }

    public Set<String> getServices() {
        return serviceMap.keySet();
    }


    @Override
    public BigDecimal configVersionNumber() {
        return timestamp == null ? BigDecimal.ZERO : new BigDecimal(timestamp);
    }

    @Override
    public VersionConfig setupWithDefault() {
        return null;
    }
}
