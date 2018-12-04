package com.github.pampas.core.base;

import com.github.pampas.core.server.GatewayServer;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
public class PampasContext {

    private static volatile GatewayServer CURRENT_SERVER = null;

    public static void setCurrentServer(GatewayServer gatewayServer) {
        CURRENT_SERVER = gatewayServer;
    }

    public static GatewayServer getCurrentServer() {
        return CURRENT_SERVER;
    }

}
