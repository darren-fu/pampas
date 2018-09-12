package com.github.pampas.core.route;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.route.Selector;
import com.github.pampas.core.route.rule.DubboRule;
import com.github.pampas.core.route.rule.GrpcRule;
import com.github.pampas.core.route.rule.HttpRule;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * Description: 请求选择器
 * 基于Rule将request路由到不同service及接口
 * User: darrenfu
 * Date: 2018-06-04
 */
@SpiMeta(name = PampasRequestSelector.SELECTOR_NAME, order = 100)
public class PampasRequestSelector implements Selector<PampasRequest<FullHttpRequest>>, Configurable<RouteRuleConfig> {

    public static final String SELECTOR_NAME = "http-request-selector";

    private RouteRuleConfig config;


    public PampasRequestSelector() {

    }


    @Override
    public RouteRuleConfig getConfig() {
        return this.config;
    }

    @Override
    public Class<RouteRuleConfig> configClass() {
        return RouteRuleConfig.class;
    }

    @Override
    public Configurable setupWithConfig(RouteRuleConfig routeRuleConfig) {
        this.config = routeRuleConfig;
        return this;
    }

    @Override
    public String name() {
        return SELECTOR_NAME;
    }


    @Override
    public Locator select(PampasRequest<FullHttpRequest> request) {
        List<HttpRule> httpRules = config.getHttp();

        if (CollectionUtils.isNotEmpty(httpRules)) {
            for (HttpRule httpRule : httpRules) {
                boolean match = httpRule.isMatch(request);
                if (match) {
                    Locator locator = new Locator();
                    locator.setLoadBalancer(ObjectUtils.defaultIfNull(config.getLoadBalancer(), PampasConsts.LoadBalancer.RANDOM));
                    locator.setWorker(PampasConsts.Worker.HTTP);
                    locator.setMappedPath(httpRule.getMappedPath());
                    locator.setServiceName(httpRule.getService());
                    return locator;
                }
            }
        }

        //TODO: dubbo selector
        List<DubboRule> dubboRules = config.getDubbo();

        if (CollectionUtils.isNotEmpty(dubboRules)) {
            for (DubboRule dubboRule : dubboRules) {
                boolean match = dubboRule.isMatch(request);
                if (match) {
                    Locator locator = new Locator();
                    locator.setWorker(PampasConsts.Worker.DUBBO);
                    locator.setMappedPath("");
                    locator.setServiceName(dubboRule.getService());
                    return locator;
                }
            }
        }

        List<GrpcRule> grpcRules = config.getGrpc();

        if (CollectionUtils.isNotEmpty(grpcRules)) {
            for (GrpcRule grpcRule : grpcRules) {
                boolean match = grpcRule.isMatch(request);
                if (match) {
                    Locator locator = new Locator();
                    locator.setWorker(PampasConsts.Worker.GRPC);
                    locator.setMappedPath("");
                    locator.setServiceName(grpcRule.getService());
                    return locator;
                }
            }
        }


        return null;
    }


}
