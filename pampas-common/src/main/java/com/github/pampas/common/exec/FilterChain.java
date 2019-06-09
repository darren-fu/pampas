package com.github.pampas.common.exec;

import com.github.pampas.common.exec.payload.PampasRequest;
import com.github.pampas.common.exec.payload.PampasResponse;
import com.github.pampas.common.exec.payload.PampasResponseHelper;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.tools.AssertTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 过滤器chain
 * User: darrenfu
 * Date: 2018-12-06
 */
public class FilterChain {
    private static final Logger log = LoggerFactory.getLogger(FilterChain.class);

    private static ThreadLocal<FilterChain> filterChainThreadLocal = ThreadLocal.withInitial(() -> new FilterChain());

    private List<Filter> filterList = null;


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
     * 0  beforeCall
     * 1 after
     * -1 failed
     */
    private FilterStage stage;

    /**
     * Instantiates a new Filter chain.
     */
    private FilterChain() {
    }

    /**
     * Instantiates a new Filter chain.
     */
    public FilterChain(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public static FilterChain currentFilterChain() {
        return filterChainThreadLocal.get();
    }


    public FilterChain addFilter(List<Filter> filterListNeedAdd) {
        if (this.filterList == null) {
            this.filterList = new LinkedList<>();
        }
        this.filterList.addAll(filterListNeedAdd);
        return this;
    }

    public List<Filter> getFilterList() {
        return this.filterList == null ? Collections.EMPTY_LIST : this.filterList;
    }

    /**
     * 重置状态
     */
    protected void resetStatus() {
        this.stage = FilterStage.BEFORE_ROUTE;
        this.stop = false;
        this.response = null;
    }

    /**
     * 重置状态，并清空缓存
     */
    protected void resetAndClear() {
        this.stage = FilterStage.BEFORE_ROUTE;
        this.stop = false;
        this.response = null;
        this.filterList = null;
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

//    /**
//     * 停止过滤器Chain
//     */
//    public void stopFilterChain() {
//        if (this.stage == FilterStage.BEFORE_ROUTE || this.stage == FilterStage.BEFORE_CALL) {
//            throw new IllegalStateException("不能无响应终止过滤器终止");
//        }
//        this.stop = true;
//    }

    /**
     * 停止过滤器Chain，并返回指定的response
     *
     * @param gatewayResponse the gateway response
     */
    public void stopFilterChainWithResponse(PampasResponse gatewayResponse) {
        AssertTools.notNull(gatewayResponse, "不能无响应终止过滤器终止");
        this.stop = true;
        this.response = gatewayResponse;
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
    public void setCurrent(String current) {
        this.current = current;
    }

    /**
     * 更新过滤器阶段
     *
     * @param stage the stage
     */
    protected void changeStage(FilterStage stage) {
        this.stage = stage;
    }

    /**
     * 获取过滤器阶段
     *
     * @return
     */
    public FilterStage getStage() {
        return stage;
    }


    public PampasResponse executeBeforeRoute(PampasRequest gatewayRequest) {
        //执行过滤器before
        for (Filter filter : this.getFilterList()) {
            if (!this.isFilterChainStop()) {
                this.setCurrent(filter.getClass().getName());
                filter.beforeRoute(gatewayRequest, this);
            } else if (this.getResponse() != null) {
                // filter终端 并且返回response
                return this.getResponse();
            } else {
                log.warn("请求被终止:{}", filter.getClass().getSimpleName());
                return PampasResponseHelper.buildFailedResponse(new RuntimeException("没有响应，请求处理被中断"));
            }
        }
        return null;
    }


    public PampasResponse executeBeforeCall(PampasRequest gatewayRequest, Locator locator) {
        //执行过滤器before
        for (Filter filter : this.getFilterList()) {
            if (!this.isFilterChainStop()) {
                this.setCurrent(filter.getClass().getName());
                filter.beforeCall(gatewayRequest, locator, this);
            } else if (this.getResponse() != null) {
                // filter终端 并且返回response
                return this.getResponse();
            } else {
                log.warn("请求被终止:{}", filter.getClass().getSimpleName());
                return PampasResponseHelper.buildFailedResponse(new RuntimeException("没有响应，请求处理被中断"));
            }
        }
        return null;
    }


    public PampasResponse executeAfter(PampasRequest gatewayRequest, Locator locator, PampasResponse gatewayResponse) {
        //反转过滤器顺序
        //执行过滤器 onSuccess和onException
        for (int i = filterList.size() - 1; i >= 0; i--) {
            Filter filter = filterList.get(i);
            this.setCurrent(filter.getClass().getName());
            if (this.isFilterChainStop()) {
                return this.getResponse();
            }
            if (gatewayResponse.success()) {
                filter.onSuccess(gatewayRequest, locator, gatewayResponse, this);
            } else if (gatewayResponse.exception() != null) {
                filter.onException(gatewayRequest, locator, gatewayResponse.exception(), this);
            }

        }
        return null;
    }
}
