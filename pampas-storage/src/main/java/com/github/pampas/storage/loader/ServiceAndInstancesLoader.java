package com.github.pampas.storage.loader;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.discover.ServiceAndInstances;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.base.AbstractConfigLoader;
import com.github.pampas.storage.entity.DBServiceAndInstances;

import java.util.Arrays;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
@SpiMeta(name = "loader" + DBServiceAndInstances.ENTITY, key = PampasConsts.ConfigLoaderKey.SERVER_CONTEXT)
public class ServiceAndInstancesLoader extends AbstractConfigLoader<ServiceAndInstances> {

    @Override
    public Class<ServiceAndInstances> configClass() {
        return ServiceAndInstances.class;
    }

    @Override
    public ServiceAndInstances doConfigLoad() {
        ServiceAndInstances serviceAndInstances = new ServiceAndInstances();
        serviceAndInstances.addServiceAndInstance("TestService",
                Arrays.asList(
                        ServerInstance.buildWithUri("TestService", "http://localhost:7003")
                ));
        return serviceAndInstances;
    }

}
