package com.github.pampas.core.route;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.Configurable;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.route.Selector;
import com.github.pampas.common.route.rule.*;
import com.github.pampas.common.tools.AntPathMatcher;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * Description: 请求选择器
 * 基于Rule将request路由到不同service及接口
 * User: darrenfu
 * Date: 2018-06-04
 */
@SpiMeta(name = PampasRequestSelector.SELECTOR_NAME, order = 100)
public class PampasRequestSelector implements Selector<PampasRequest<FullHttpRequest>>, Configurable<RouteRuleConfig> {

    private static final Logger log = LoggerFactory.getLogger(PampasRequestSelector.class);

    public static final String SELECTOR_NAME = "http-request-selector";


    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

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
        String host = request.requestData().headers().get("Host");
        RulePackage[] rulePackages = config.getRulePackages();
        for (RulePackage rulePackage : rulePackages) {
            if (StringUtils.isNotEmpty(rulePackage.getMappingHost())) {
                if (!rulePackage.getMappingHost().equals(host)) {
                    log.debug("匹配HOST失败，RULE:{}, HOST:{}", rulePackage.getMappingHost(), host);
                    continue;
                }
            }
            for (AbstractRule rule : rulePackage.getRuleList()) {
                boolean match = rule.isMatch(request);
                log.trace("匹配路由规则:{},match:{},request:{}", rule, match, request.shotString());
                if (match) {
                    if (rule instanceof HttpRule) {
                        Locator locator = new Locator();
                        locator.setServiceName(rule.getService());
                        locator.setLoadBalancer(PampasConsts.LoadBalancer.RANDOM);
                        locator.setWorker(PampasConsts.Worker.HTTP);
                        if (rule.getMappingStrategy() == MappingStrategyEnum.APPOINT) {
                            locator.setMappedPath(rule.getMappingPath());
                        } else if (rule.getMappingStrategy() == MappingStrategyEnum.STRIP) {
                            if (rule.getMappingPath() != null && rule.getMappingPath().length() > 1) {
                                String result = StringUtils.substringAfter(request.path(), rule.getMappingPath());
                                locator.setMappedPath(result);
                            } else {
                                locator.setMappedPath(request.path());
                            }
                        }
                        locator.setHostStrategy(rule.getHostStrategy().getValue());
                        if (rule.getHostStrategy() == HostStrategyEnum.APPOINT) {
                            locator.setInstanceList(ObjectUtils.defaultIfNull(rule.getMappingServerInstanceList(), Collections.emptyList()));
                        }
                        log.debug("匹配路由规则完成，rule:{}, locator:{},request:{}", rule, locator, request.shotString());
                        return locator;
                    }
                }
            }
        }
        return null;
    }

}
