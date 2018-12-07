package com.github.pampas.storage.service;

import com.github.pampas.common.config.ListableConfig;
import com.github.pampas.common.tools.StreamTools;
import com.github.pampas.storage.entity.GatewayConfig;
import com.github.pampas.storage.entity.GatewayConfigCondition;
import com.github.pampas.storage.mapper.GatewayConfigMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-06
 */
@Service
public class GatewayConfigServiceImpl implements GatewayConfigService {

    private static final Logger log = LoggerFactory.getLogger(GatewayConfigServiceImpl.class);

    @Autowired
    private GatewayConfigMapper gatewayConfigMapper;

    @Override
    public void save(String group, String gatewayInstanceId, String type, List<ListableConfig.PropDefine> configList) {
        GatewayConfigCondition configCondition = new GatewayConfigCondition();
        GatewayConfigCondition.Criteria criteria = configCondition.createCriteria();
        criteria.andGroupEqualTo(group);
        List<GatewayConfig> gatewayConfigList = gatewayConfigMapper.selectByExample(configCondition);

        Map<String, GatewayConfig> configMap = StreamTools.toMap(gatewayConfigList, GatewayConfig::getKey);

        for (ListableConfig.PropDefine config : configList) {
            if (!configMap.containsKey(config.getKey())) {
                GatewayConfig gatewayConfig = new GatewayConfig();
                gatewayConfig.setType(type);
                gatewayConfig.setKey(config.getKey());
                gatewayConfig.setLabel(config.getLabel());
                gatewayConfig.setDefaultValue(config.getDefaultValue());
                gatewayConfig.setGroup(group);
                gatewayConfig.setGatewayInstanceId(config.getLevel() == ListableConfig.ConfigLevelEnum.INSTANCE ? gatewayInstanceId : null);
                gatewayConfigMapper.insertSelective(gatewayConfig);
                log.info("新增网关配置项:{}", config);
            }
        }
    }

    @Override
    public List<GatewayConfig> getGatewayConfig(String group, String gatewayInstanceId, String type) {
        GatewayConfigCondition configCondition = new GatewayConfigCondition();
        GatewayConfigCondition.Criteria criteria = configCondition.createCriteria();
        criteria.andGroupEqualTo(group);
        if (StringUtils.isNotEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }
        List<GatewayConfig> gatewayConfigList = gatewayConfigMapper.selectByExample(configCondition);
        log.info("查询获取当前网关配置项{}条:{}", gatewayConfigList.size(), gatewayConfigList);
        return gatewayConfigList;
    }
}
