package com.github.pampas.core.base;

import com.github.pampas.core.server.GatewayServer;
import com.github.pampas.core.server.ServerState;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-17
 */
public class PampasContext {

    protected static final WeakHashMap<GatewayServer, ServerState> serverMap = new WeakHashMap<>();

    public static void updateServerState(GatewayServer gatewayServer, ServerState serverState) {
        serverMap.put(gatewayServer, serverState);
    }

    public static List<GatewayServer> gatewayServers() {
        Set<GatewayServer> gatewayServers = serverMap.keySet();
        return new ArrayList<>(gatewayServers);
    }

}
