package com.github.pampas.core.filter;

import com.github.pampas.common.config.DefinableConfig;
import com.github.pampas.common.extension.SpiMeta;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-06
 */
@SpiMeta(name = AuthFilterConfig.SPI_META_NAME)
@Data
public class AuthFilterConfig implements DefinableConfig {

    public static final String SPI_META_NAME = "config-auth-filter";

    private List<PropDefine> propDefineList = new ArrayList<>();


    private ConcurrentHashMap<String, String> propMap;

    public AuthFilterConfig() {
        PropDefine propDefine = new PropDefine("redis.address", "REDIS地址", "localhost:6379");
        this.propDefineList.add(propDefine);
        this.propMap = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, String> getPropMap() {
        return propMap;
    }

    @Override
    public List<PropDefine> propDefines() {
        return propDefineList;
    }

    @Override
    public BigDecimal configVersionNumber() {
        return BigDecimal.ONE;
    }
}
