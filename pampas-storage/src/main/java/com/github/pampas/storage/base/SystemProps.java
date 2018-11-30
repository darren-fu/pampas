package com.github.pampas.storage.base;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 系统基本配置
 * author: fuliang
 * date: 2017/2/24
 */
@Component
public class SystemProps implements EnvironmentAware {

    private static Environment env;


    private static String gatewayVersion;


    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }


    /**
     * Gets framework version.
     *
     * @return the framework version
     */
    public static String getGatewayVersion() {
        if (StringUtils.isEmpty(gatewayVersion) && env != null && StringUtils.isNotEmpty(env.getProperty("gateway.version"))) {
            SystemProps.gatewayVersion = env.getProperty("gateway.version");
        }
        return gatewayVersion;
    }
}
