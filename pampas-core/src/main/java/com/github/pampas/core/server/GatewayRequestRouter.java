package com.github.pampas.core.server;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.ConfigLoader;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.tools.AssertTools;
import com.github.pampas.common.tools.ResponseTools;
import com.github.pampas.common.tools.ext.StringTools;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.util.List;

import static com.github.pampas.common.base.PampasConsts.GatewayOperation.RERRESH_CONFIG_BY_KEY;
import static com.github.pampas.common.base.PampasConsts.GatewayOperation.RERRESH_CONFIG_BY_NAME;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-07
 */
public class GatewayRequestRouter {


    public static boolean isGatewayRequest(String path) {
        return path.startsWith(PampasConsts.GATEWAY_REQ_PREFIX);
    }


    public static HttpResponse handleGatewayRequest(HttpRequest httpRequest) throws ClassNotFoundException {
        String requestPath = StringTools.trimLeadingCharacter(httpRequest.uri(), '/');

        String[] splits = requestPath.split("/");
        AssertTools.isTrue(splits != null && splits.length > 1, "不是合法的网关内部请求");
        AssertTools.isTrue(("/" + splits[0]).equals(PampasConsts.GATEWAY_REQ_PREFIX), "不是合法的网关内部请求");

        String operation = splits[1];

        if (RERRESH_CONFIG_BY_KEY.equals(operation)) {
            String spiKey = splits[2];
            List<ConfigLoader> instancesByKey = SpiContext.getContext(ConfigLoader.class).getSpiInstancesByKey(spiKey);
            for (ConfigLoader configLoader : instancesByKey) {
                configLoader.refreshConfig();
            }
        } else if (RERRESH_CONFIG_BY_NAME.equals(operation)) {
            String spiName = splits[2];
            ConfigLoader instanceByName = SpiContext.getContext(ConfigLoader.class).getSpiInstanceByName(spiName);
            instanceByName.refreshConfig();
        }
        return ResponseTools.OK;
    }
}
