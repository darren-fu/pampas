package com.github.pampas.storage.service;

import com.github.pampas.storage.entity.RouteRule;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-04
 */
public interface RouteRuleService {

    List<RouteRule> getGatewayRelRouteRule(String instanceId);

}
