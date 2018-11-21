package com.github.pampas.storage.service;

import com.github.pampas.storage.entity.ServiceInstance;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-11-15
 */
public interface ServiceInstanceService {

    ServiceInstance getServiceInstance(Integer id);

    List<ServiceInstance> getServiceInstanceList(String... serviceName);


    void save(ServiceInstance serviceInstance);


}
