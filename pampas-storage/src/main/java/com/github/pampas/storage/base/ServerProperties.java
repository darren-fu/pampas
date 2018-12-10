package com.github.pampas.storage.base;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-08
 */
@Data
@Component
@ConfigurationProperties(prefix = "pampas")
public class ServerProperties implements InitializingBean {
    private String group;

    private Integer port;

    private String id;

    private Integer boss;

    private Integer worker;

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
