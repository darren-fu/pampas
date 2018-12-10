package com.github.pampas.storage.service;

import com.github.pampas.storage.entity.GatewaySpi;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-10
 */
public interface GatewaySpiService {

    void saveGatewaySpi(String gatewayGroup, String gatewayInstanceId, List<GatewaySpi> spiList);

    List<GatewaySpi> getSpiListInGateway(String gatewayGroup, String gatewayInstanceId);

}
