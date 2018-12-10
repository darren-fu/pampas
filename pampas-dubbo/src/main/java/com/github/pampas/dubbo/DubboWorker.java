package com.github.pampas.dubbo;

import com.github.pampas.common.exec.AbstractWorker;
import com.github.pampas.common.exec.Caller;
import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.tools.JsonTools;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

/**
 * @author 高建华
 * @date 2018/4/24 下午1:23
 */
public class DubboWorker extends AbstractWorker<FullHttpRequest, FullHttpResponse> {

    private static final Logger log = LoggerFactory.getLogger(DubboWorker.class);

    private Caller caller = new DubboCaller();

    @Override
    protected void doAfter(String threadName) {

    }

    @Override
    public CompletableFuture<PampasResponse<FullHttpResponse>> doExecute(PampasRequest<FullHttpRequest> req, Locator locator) throws IOException {
        String content = req.requestData().content().toString(Charset.forName("UTF-8"));
        DubboRequest dubboRequest = JsonTools.NON_NULL.fromJson(content,DubboRequest.class);
        return caller.asyncCall(dubboRequest,null);
    }

}
