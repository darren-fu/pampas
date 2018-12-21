package com.github.pampas.common.exec;

import com.github.pampas.common.exec.payload.PampasResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 过滤器chain
 * User: darrenfu
 * Date: 2018-12-06
 */
@SuppressWarnings("unused")
public class FilterChain {

    //当前filter 标识
    private String current;

    // filter chain是否已经中断
    private boolean stop;
    /**
     * 缓存
     */
    private final Map<String, Object> cache = new ConcurrentHashMap<>();

    /**
     * 响应
     */
    private PampasResponse response;

    /**
     * 0  before
     * 1 after
     * -1 failed
     */
    private int stage;


    /**
     * Instantiates a new Filter chain.
     */
    protected FilterChain() {

    }

    /**
     * 重置状态
     */
    protected void resetStatus() {
        this.stage = 0;
        this.stop = false;
        this.response = null;
    }

    /**
     * 重置状态，并清空缓存
     */
    protected void resetAndClear() {
        this.stage = 0;
        this.stop = false;
        this.response = null;
        cache.clear();
    }


    /**
     * 过滤器Chain是否已经终止
     *
     * @return the boolean
     */
    public boolean isFilterChainStop() {
        return stop;
    }

    /**
     * 停止过滤器Chain
     */
    public void stopFilterChain() {
        if (this.stage != 0) {
            throw new IllegalStateException("不能无响应终止过滤器终止");
        }
        this.stop = true;
    }

    /**
     * 停止过滤器Chain，并返回指定的response
     *
     * @param pampasResponse the pampas response
     */
    public void stopFilterChainWithResponse(PampasResponse pampasResponse) {
        this.stop = true;
        this.response = pampasResponse;
    }

    /**
     * 获取response
     *
     * @return the response
     */
    public PampasResponse getResponse() {
        return response;
    }

    /**
     * 获取缓存的key
     *
     * @param <T> the type parameter
     * @param key the key
     * @return the cache val
     */
    public <T> T getCacheVal(String key) {
        return (T) cache.get(current + key);
    }

    /**
     * 缓存k-v
     *
     * @param <T> the type parameter
     * @param key the key
     * @param val the val
     */
    public <T> void cacheVal(String key, T val) {
        cache.put(current + key, val);
    }


    /**
     * 获取当前Filter 标识
     *
     * @return the current
     */
    public String getCurrent() {
        return current;
    }

    /**
     * 设置当前Filter 标识
     *
     * @param current the current
     */
    protected void setCurrent(String current) {
        this.current = current;
    }

    /**
     * 更新过滤器阶段
     *
     * @param stage the stage
     */
    protected void changeStage(int stage) {
        this.stage = stage;
    }

    /**
     * 获取过滤器阶段
     *
     * @return
     */
    public int getStage() {
        return stage;
    }
}
