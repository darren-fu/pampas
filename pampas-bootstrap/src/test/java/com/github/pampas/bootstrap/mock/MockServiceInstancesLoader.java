package com.github.pampas.bootstrap.mock;

import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.discover.ServiceAndInstances;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.core.base.AbstractConfigLoader;

import java.util.Arrays;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
@SpiMeta(order = 0)
public class MockServiceInstancesLoader extends AbstractConfigLoader<ServiceAndInstances> {
    @Override
    public Class<ServiceAndInstances> configClass() {
        return ServiceAndInstances.class;
    }

    @Override
    public ServiceAndInstances doConfigLoad() {
        ServiceAndInstances serviceAndInstances = new ServiceAndInstances();
        serviceAndInstances.addServiceAndInstance("TestService",
                Arrays.asList(
                        ServerInstance.buildWithUri("TestService", "http://localhost:7001"),
                        ServerInstance.buildWithUri("TestService", "http://localhost:7002")
                ));
        return serviceAndInstances;
    }


}
