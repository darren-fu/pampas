package com.github.pampas.storage.runtime;

import com.github.pampas.common.config.ConfigLoader;
import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.discover.ServerContext;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.discover.ServiceAndInstancesConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.storage.loader.ServiceAndInstancesLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-07
 */

@SpiMeta(name = StorageServerContext.SPI_META_NAME, desc = "基于存储的服务发现容器", order = 10)
public class StorageServerContext implements ServerContext, Configurable<ServiceAndInstancesConfig> {

    public static final String SPI_META_NAME = "server-context-storage";

    private ServiceAndInstancesConfig serviceAndInstancesConfig;

    private Long refreshTimestamp;

    @Override
    public ServiceAndInstancesConfig getConfig() {
        return serviceAndInstancesConfig;
    }

    @Override
    public Class<ServiceAndInstancesConfig> configClass() {
        return ServiceAndInstancesConfig.class;
    }

    @Override
    public Configurable setupWithConfig(ServiceAndInstancesConfig t) {
        this.serviceAndInstancesConfig = t;
        this.refreshTimestamp = System.currentTimeMillis();
        return this;
    }

    @Override
    public long lastRefreshedTime() {
        return this.refreshTimestamp;
    }

    @Override
    public List<String> getAllServiceName() {
        if (serviceAndInstancesConfig != null) {
            Set<String> nameSet = serviceAndInstancesConfig.getServices();
            ArrayList<String> nameList = new ArrayList<>(nameSet.size());
            nameList.addAll(nameSet);
            return nameList;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<ServerInstance> getServerList(String serviceName) {
        if (serviceAndInstancesConfig != null) {
            return serviceAndInstancesConfig.getInstances(serviceName);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void refreshServerList() {
        ConfigLoader configLoader = SpiContext.getContext(ConfigLoader.class).getSpiInstanceByName(ServiceAndInstancesLoader.SPI_META_NAME);
        configLoader.refreshConfig();
    }

    @Override
    public void refreshServerList(String serviceName) {
        throw new UnsupportedOperationException("不支持此操作");
    }

    @Override
    public void clear() {
        this.serviceAndInstancesConfig = null;
    }

    @Override
    public List<ServerInstance> addServerList(List<ServerInstance> instanceList) {
        throw new UnsupportedOperationException("不支持此操作");
    }


}
