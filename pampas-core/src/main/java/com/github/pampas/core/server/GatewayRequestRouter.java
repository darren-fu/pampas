package com.github.pampas.core.server;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.config.ConfigLoader;
import com.github.pampas.common.config.DefinableConfig;
import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.tools.AssertTools;
import com.github.pampas.common.tools.ResponseTools;
import com.github.pampas.common.tools.ext.StringTools;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.github.pampas.common.base.PampasConsts.GatewayOperation.*;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-07
 */
public class GatewayRequestRouter {

    private static final Logger log = LoggerFactory.getLogger(GatewayRequestRouter.class);

    public static boolean isGatewayRequest(String path) {
        return path.startsWith(PampasConsts.GATEWAY_REQ_PREFIX);
    }


    public static HttpResponse handleGatewayRequest(HttpRequest httpRequest) throws ClassNotFoundException {
        String requestPath = StringTools.trimLeadingCharacter(httpRequest.uri(), '/');

        String[] splits = requestPath.split("/");
        AssertTools.isTrue(splits != null && splits.length > 1, "不是合法的网关内部请求");
        AssertTools.isTrue(("/" + splits[0]).equals(PampasConsts.GATEWAY_REQ_PREFIX), "不是合法的网关内部请求");
        String operation = splits[1];
        log.info("接收网关请求[{}]:{},{}", operation, httpRequest.uri(), httpRequest.toString());

        if (REFRESH_CONFIG_BY_KEY.equals(operation)) {
            String spiKey = splits[2];
            List<ConfigLoader> instancesByKey = SpiContext.getContext(ConfigLoader.class).getSpiInstancesByKey(spiKey);
            for (ConfigLoader configLoader : instancesByKey) {
                configLoader.refreshConfig();
                log.info("刷新[{}]完成", configLoader.configClass().getName());
            }
        } else if (REFRESH_CONFIG_BY_NAME.equals(operation)) {
            String spiName = splits[2];
            ConfigLoader instanceByName = SpiContext.getContext(ConfigLoader.class).getSpiInstanceByName(spiName);
            instanceByName.refreshConfig();
            log.info("刷新[{}]完成", instanceByName.configClass().getName());
        } else if (REFRESH_DEFINABLE_CONFIG_BY_CLASS_NAME.equals(operation)) {
            String configSpiName = splits[2];
            Class<DefinableConfig> configSpiClass = SpiContext.getContext(DefinableConfig.class).getSpiClass(configSpiName);

            List<ConfigLoader> configLoaderList = SpiContext.getContext(ConfigLoader.class).getSpiInstances();
            for (ConfigLoader configLoader : configLoaderList) {
                if (configLoader.configClass().equals(configSpiClass)) {
                    configLoader.refreshConfig();
                    log.info("刷新[{}]完成", configSpiClass);
                }
            }

        }
        return ResponseTools.buildResponse("OK");
    }
}
