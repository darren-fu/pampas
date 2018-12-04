package com.github.pampas.storage.service;

import com.github.pampas.storage.entity.GatewayRouteRuleRel;
import com.github.pampas.storage.entity.GatewayRouteRuleRelCondition;
import com.github.pampas.storage.entity.RouteRule;
import com.github.pampas.storage.entity.RouteRuleCondition;
import com.github.pampas.storage.mapper.GatewayRouteRuleRelMapper;
import com.github.pampas.storage.mapper.RouteRuleMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-04
 */
@Service
public class RouteRuleserviceImpl implements RouteRuleService {

    private static final Logger log = LoggerFactory.getLogger(RouteRuleserviceImpl.class);

    @Autowired
    private RouteRuleMapper routeRuleMapper;

    @Autowired
    private GatewayRouteRuleRelMapper gatewayRouteRuleRelMapper;

    @Override
    public List<RouteRule> getGatewayRelRouteRule(String instanceId) {

        GatewayRouteRuleRelCondition relCondition = new GatewayRouteRuleRelCondition();
        relCondition.createCriteria().andGatewayInstanceIdEqualTo(instanceId);

        List<GatewayRouteRuleRel> gatewayRouteRuleRels = gatewayRouteRuleRelMapper.selectByExample(relCondition);
        log.info("网关:{}关联rule{}个:{}", instanceId, gatewayRouteRuleRels.size(), gatewayRouteRuleRels);
        if (CollectionUtils.isEmpty(gatewayRouteRuleRels)) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> ruleIdList = gatewayRouteRuleRels.stream().map(GatewayRouteRuleRel::getRouteRuleId).collect(Collectors.toList());
        RouteRuleCondition ruleCondition = new RouteRuleCondition();
        ruleCondition.createCriteria().andIdIn(ruleIdList).andStatusEqualTo(true);

        List<RouteRule> routeRuleList = routeRuleMapper.selectByExampleWithBLOBs(ruleCondition);
        log.info("网关:{}关联规则,ID:{}:{}", instanceId, ruleIdList, routeRuleList);
        return routeRuleList;
    }
}
