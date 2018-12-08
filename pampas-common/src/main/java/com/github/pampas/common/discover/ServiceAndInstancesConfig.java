package com.github.pampas.common.discover;

import com.github.pampas.common.config.VersionConfig;
import com.github.pampas.common.extension.SpiMeta;
import com.google.common.base.MoreObjects;
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
@SpiMeta(name = "config-service-instances")
@Getter
public class ServiceAndInstancesConfig implements VersionConfig {
    public static final String SPI_META_NAME = "config-service-instances";

    private Map<String, List<ServerInstance>> serviceMap;

    @Setter
    private Long timestamp;

    public ServiceAndInstancesConfig() {
        this.serviceMap = new ConcurrentHashMap<>();
    }


    public ServiceAndInstancesConfig addServiceAndInstance(String serviceName, List<ServerInstance> instanceList) {
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


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("serviceMap", serviceMap)
                .add("timestamp", timestamp)
                .toString();
    }
}
