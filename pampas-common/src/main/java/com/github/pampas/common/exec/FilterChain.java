package com.github.pampas.common.exec;

import com.github.pampas.common.exec.payload.PampasResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-06
 */
public class FilterChain {

    protected FilterChain() {

    }

    private String current;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    private boolean stop;

    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    private PampasResponse response;


    protected void resetStatus() {
        this.stop = false;
        this.response = null;
    }
    protected void resetAndClear() {
        this.stop = false;
        this.response = null;
        cache.clear();
    }

    public <T> T getCacheVal(String key) {
        return (T) cache.get(current+ key);
    }

    public <T> void cacheVal(String key, T val) {
        cache.put(current + key, val);
    }

    public boolean isFilterChainStop() {
        return stop;
    }

    public void stopFilterChain() {
        this.stop = true;
    }

    public void stopFilterChainWithResponse(PampasResponse pampasResponse) {
        this.stop = true;
        this.response = pampasResponse;
    }

    public PampasResponse getResponse() {
        return response;
    }
}
