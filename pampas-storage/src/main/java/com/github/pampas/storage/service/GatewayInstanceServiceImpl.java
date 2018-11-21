package com.github.pampas.storage.service;

import com.github.pampas.common.tools.AssertTools;
import com.github.pampas.storage.entity.GatewayInstance;
import com.github.pampas.storage.entity.GatewayInstanceCondition;
import com.github.pampas.storage.mapper.GatewayInstanceMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-11-15
 */
@Service
public class GatewayInstanceServiceImpl implements GatewayInstanceService {

    private static final Logger log = LoggerFactory.getLogger(GatewayInstanceServiceImpl.class);

    @Autowired
    private GatewayInstanceMapper gatewayInstanceMapper;

    @Override
    public void save(GatewayInstance gatewayInstance) {

        GatewayInstanceCondition condition = new GatewayInstanceCondition();
        condition.createCriteria().andInstanceIdEqualTo(gatewayInstance.getInstanceId());

        List<GatewayInstance> gatewayInstances = gatewayInstanceMapper.selectByExample(condition);

        if (CollectionUtils.isEmpty(gatewayInstances)) {
            gatewayInstanceMapper.insert(gatewayInstance);
            log.info("新增网关:{}", gatewayInstance);
        } else {
            AssertTools.isTrue(gatewayInstances.size() == 1, "存在重复的gateway:" + gatewayInstance.getInstanceId());

            gatewayInstance.setId(gatewayInstances.get(0).getId());
            gatewayInstanceMapper.updateByPrimaryKeySelective(gatewayInstance);
            log.info("更新网关:{}", gatewayInstance);
        }


    }
}
