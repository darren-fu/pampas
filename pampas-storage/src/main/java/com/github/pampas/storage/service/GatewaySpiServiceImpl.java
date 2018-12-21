package com.github.pampas.storage.service;

import com.github.pampas.common.tools.StreamTools;
import com.github.pampas.common.tools.ext.ObjectUtils;
import com.github.pampas.storage.entity.GatewaySpi;
import com.github.pampas.storage.entity.GatewaySpiCondition;
import com.github.pampas.storage.mapper.GatewaySpiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-10
 */
@Service
public class GatewaySpiServiceImpl implements GatewaySpiService {

    private static final Logger log = LoggerFactory.getLogger(GatewaySpiServiceImpl.class);

    @Autowired
    private GatewaySpiMapper gatewaySpiMapper;

    @Override
    public void saveGatewaySpi(String gatewayGroup, String gatewayInstanceId, List<GatewaySpi> spiList) {
        if (spiList == null || spiList.size() < 1) {
            return;
        }
        spiList.forEach(v -> v.setGatewayInstanceId(gatewayInstanceId));

        List<GatewaySpi> spiListInGateway = this.getSpiListInGateway(gatewayGroup, gatewayInstanceId);
        Map<String, GatewaySpi> existSpiMap = StreamTools.toMap(spiListInGateway, GatewaySpi::getSpiClass);
        for (GatewaySpi gatewaySpi : spiList) {
            GatewaySpi existSpi = existSpiMap.get(gatewaySpi.getSpiClass());
            if (existSpi == null) {
                gatewaySpiMapper.insertSelective(gatewaySpi);
                log.info("新增网关SPI:{},{},{}", gatewayGroup, gatewayInstanceId, gatewaySpi);
            } else {
                if (!ObjectUtils.nullSafeEquals(existSpi.getSpiInterfaceDesc(), gatewaySpi.getSpiInterfaceDesc())
                        || !ObjectUtils.nullSafeEquals(existSpi.getSpiDesc(), gatewaySpi.getSpiDesc())) {
                    GatewaySpi updateSpi = new GatewaySpi();
                    updateSpi.setId(existSpi.getId());
                    updateSpi.setSpiDesc(gatewaySpi.getSpiDesc());
                    updateSpi.setSpiInterfaceDesc(gatewaySpi.getSpiInterfaceDesc());
                    gatewaySpiMapper.updateByPrimaryKeySelective(updateSpi);
                    log.info("更新网关SPI:{},{},{}", gatewayGroup, gatewayInstanceId, gatewaySpi);
                }
            }
        }
    }

    @Override
    public List<GatewaySpi> getSpiListInGateway(String gatewayGroup, String gatewayInstanceId) {
        GatewaySpiCondition condition = new GatewaySpiCondition();
        GatewaySpiCondition.Criteria criteria = condition.createCriteria();

        criteria.andGatewayInstanceIdEqualTo(gatewayInstanceId);
        List<GatewaySpi> gatewaySpiList = gatewaySpiMapper.selectByExample(condition);
        log.info("获取网关SPI列表:{}", gatewaySpiList);
        return gatewaySpiList;
    }
}
