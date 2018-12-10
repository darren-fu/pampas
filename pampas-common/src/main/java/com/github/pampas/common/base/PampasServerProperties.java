package com.github.pampas.common.base;

import lombok.Data;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-08
 */
@Data
public class PampasServerProperties {
    private String group;
    private Integer port;

    private String gatewayId;

    private Integer workThreads;

}
