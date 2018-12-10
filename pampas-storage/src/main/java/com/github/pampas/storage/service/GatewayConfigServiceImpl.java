package com.github.pampas.storage.service;

import com.github.pampas.common.config.DefinableConfig;
import com.github.pampas.common.tools.AssertTools;
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
    public void save(String group, String gatewayInstanceId,
                     String spiInterface, String spiClass, String spiName, String spiDesc,
                     List<DefinableConfig.PropDefine> configList) {
        AssertTools.notNull(group, "网关分组不能为空");
        AssertTools.notNull(spiInterface, "spiInterface不能为空");
        AssertTools.notNull(spiClass, "spiClass不能为空");

        GatewayConfigCondition configCondition = new GatewayConfigCondition();
        GatewayConfigCondition.Criteria criteria = configCondition.createCriteria();
        criteria.andGatewayGroupEqualTo(group);
        criteria.andConfigSpiClassEqualTo(spiClass);
        List<GatewayConfig> gatewayConfigList = gatewayConfigMapper.selectByExample(configCondition);

        Map<String, GatewayConfig> configMap = StreamTools.toMap(gatewayConfigList, GatewayConfig::getKey);

        for (DefinableConfig.PropDefine config : configList) {
            if (!configMap.containsKey(config.getKey())) {
                GatewayConfig gatewayConfig = new GatewayConfig();
                gatewayConfig.setGatewayInstanceId(config.getLevel() == DefinableConfig.ConfigLevelEnum.INSTANCE ? gatewayInstanceId : null);
                gatewayConfig.setGatewayGroup(group);
                gatewayConfig.setConfigSpiInterface(spiInterface);
                gatewayConfig.setConfigSpiClass(spiClass);
                gatewayConfig.setConfigSpiName(spiName);
                gatewayConfig.setConfigSpiDesc(spiDesc);
                gatewayConfig.setKey(config.getKey());
                gatewayConfig.setLabel(config.getLabel());
                gatewayConfig.setDefaultValue(config.getDefaultValue());
                gatewayConfigMapper.insertSelective(gatewayConfig);
                log.info("新增网关配置项:{}", config);
            }
        }
    }

    @Override
    public List<GatewayConfig> getGatewayConfig(String group, String gatewayInstanceId, String spiClass) {
        GatewayConfigCondition configCondition = new GatewayConfigCondition();
        GatewayConfigCondition.Criteria criteria = configCondition.createCriteria();
        criteria.andGatewayGroupEqualTo(group);
        if (StringUtils.isNotEmpty(spiClass)) {
            criteria.andConfigSpiClassEqualTo(spiClass);
        }
        List<GatewayConfig> gatewayConfigList = gatewayConfigMapper.selectByExample(configCondition);
        log.info("查询获取当前网关配置项{}条:{}", gatewayConfigList.size(), gatewayConfigList);
        return gatewayConfigList;
    }
}
