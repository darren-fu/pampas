package com.github.pampas.dubbo;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 高建华
 * @date 2018/4/24 下午3:28
 */
@Data
public class DubboRequest implements Serializable {
    private String service;
    private String method;
    private JSONArray params;
}
