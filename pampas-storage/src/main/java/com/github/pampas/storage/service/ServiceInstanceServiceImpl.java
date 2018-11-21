package com.github.pampas.storage.service;

import com.github.pampas.common.tools.AssertTools;
import com.github.pampas.storage.entity.ServiceInstance;
import com.github.pampas.storage.entity.ServiceInstanceCondition;
import com.github.pampas.storage.mapper.ServiceInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-11-15
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class ServiceInstanceServiceImpl implements ServiceInstanceService {

    private static final Logger log = LoggerFactory.getLogger(ServiceInstanceServiceImpl.class);

    @Autowired
    private ServiceInstanceMapper serviceInstanceMapper;

    @Override
    public ServiceInstance getServiceInstance(Integer id) {
        ServiceInstance serviceInstance = serviceInstanceMapper.selectByPrimaryKey(id);
        return serviceInstance;
    }

    @Override
    public List<ServiceInstance> getServiceInstanceList(String... serviceName) {
        ServiceInstanceCondition condition = new ServiceInstanceCondition();
        if (serviceName != null && serviceName.length > 0) {
            condition.createCriteria().andServiceNameIn(Arrays.asList(serviceName));
        }
        condition.setPageInfo(1, 100);
        List<ServiceInstance> serviceInstances = serviceInstanceMapper.selectByExample(condition);
        return serviceInstances;
    }

    @Override
    public void save(ServiceInstance serviceInstance) {
        AssertTools.notNull(serviceInstance, "serviceInstance不能为空");
        AssertTools.notEmpty(serviceInstance.getServiceName(), "server name不能为空");
        AssertTools.notEmpty(serviceInstance.getInstanceId(), "instance ID不能为空");
        ServiceInstanceCondition condition = new ServiceInstanceCondition();
        condition.createCriteria().andServiceNameEqualTo(serviceInstance.getServiceName())
                .andInstanceIdEqualTo(serviceInstance.getInstanceId());

        if (serviceInstance.getId() != null) {
            ServiceInstance existInstance = this.getServiceInstance(serviceInstance.getId());
            AssertTools.notNull(existInstance, "不存在此instance:" + serviceInstance.getId());
            int i = serviceInstanceMapper.updateByPrimaryKeySelective(serviceInstance);
            log.info("更新服务实例:{}", serviceInstance);
        } else {
            int i = serviceInstanceMapper.insertSelective(serviceInstance);
            log.info("新增服务实例:{}", serviceInstance);
        }
    }
}
