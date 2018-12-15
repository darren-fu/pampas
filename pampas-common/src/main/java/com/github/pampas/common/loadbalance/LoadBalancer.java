package com.github.pampas.common.loadbalance;

import com.github.pampas.common.discover.ServerContext;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;
import com.github.pampas.common.route.Locator;

import java.util.Set;

/**
 * 负载均衡接口
 * Created by darrenfu on 17-6-26.
 */
@Spi(scope = Scope.SINGLETON, desc = "负载均衡器")
public interface LoadBalancer {

    /**
     * 负载均衡，服务路由
     *
     * @param context              服务实例Context
     * @param locator              Resty命令
     * @param excludeInstanceIdSet 排除的server instance Id列表，如，重试时需要排除被选择过的服务实例
     * @return the server instance
     */
    ServerInstance choose(ServerContext context, Locator locator, Set<String> excludeInstanceIdSet);
}
