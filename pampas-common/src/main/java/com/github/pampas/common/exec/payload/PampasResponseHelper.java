package com.github.pampas.common.exec.payload;

import com.github.pampas.common.exec.payload.HttpResponseHelper;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.tools.JsonTools;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import static com.github.pampas.common.exec.payload.HttpResponseHelper.EMPTY;
import static com.github.pampas.common.exec.payload.HttpResponseHelper.JSON_TYPE;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-21
 */
public class PampasResponseHelper {

    public static PampasResponse buildSuccessResponse(Object responseData) {
        PampasResponse.SuccessPampasResponse pampasResponse = new PampasResponse.SuccessPampasResponse(responseData);
        return pampasResponse;
    }

    public static PampasResponse buildFailedResponse(Throwable throwable) {
        PampasResponse.ExceptionPampasResponse pampasResponse = new PampasResponse.ExceptionPampasResponse(throwable);
        return pampasResponse;
    }


    /**
     * 基于PampasResponse，创建一个HttpResponse
     *
     * @param response PampasResponse
     * @return HttpResponse
     */
    public static HttpResponse convertPampasResponseToHttpResponse(PampasResponse response) {
        final PampasResponse pampasResponse = response;
        Object responseData = pampasResponse.responseData();
        if (pampasResponse.success()) {
            if (responseData == null) {
                return EMPTY;
            } else if (responseData instanceof HttpResponse) {
                return (HttpResponse) responseData;
            } else {
                return HttpResponseHelper.buildHttpTextResponse(JsonTools.NON_EMPTY.toJson(responseData), HttpResponseStatus.OK, JSON_TYPE);
            }
        } else {
            if (responseData == null) {
                return HttpResponseHelper.buildHttpTextResponse(pampasResponse.exception() == null ? null : pampasResponse.exception().getMessage(), HttpResponseStatus.BAD_GATEWAY, JSON_TYPE);
            } else if (responseData instanceof HttpResponse) {
                return (HttpResponse) responseData;
            } else {
                return HttpResponseHelper.buildHttpTextResponse(JsonTools.NON_EMPTY.toJson(responseData), HttpResponseStatus.BAD_GATEWAY, JSON_TYPE);
            }
        }
    }


}
