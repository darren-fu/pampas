package com.github.pampas.dubbo;

import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.exec.Caller;

import java.util.concurrent.CompletableFuture;

/**
 * @author 高建华
 * @date 2018/4/24 下午3:27
 */
public class DubboCaller  implements Caller<DubboRequest, Object> {
    @Override
    public Object call(DubboRequest req, ServerInstance serverInstance) {
        return DubboServiceFactory.getInstance().genericInvoke(req);
    }

    @Override
    public CompletableFuture<Object> asyncCall(DubboRequest req, ServerInstance serverInstance) {
        return CompletableFuture.supplyAsync(() -> DubboServiceFactory.getInstance().genericInvoke(req));
    }
}
