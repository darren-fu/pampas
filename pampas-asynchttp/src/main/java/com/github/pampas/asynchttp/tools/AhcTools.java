package com.github.pampas.asynchttp.tools;

import org.asynchttpclient.Request;
import org.asynchttpclient.Response;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-07-25
 */
public class AhcTools {


    public static String requestToString(Request request){
        if(request == null){
            return "";
        }
        return request.toString().replaceAll("\t|\n", "");
    }

    public static String responseToString(Response response){
        if(response == null){
            return "";
        }
        return response.toString().replaceAll("\t|\n", "");
    }
}
