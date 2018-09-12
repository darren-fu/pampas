package com.github.pampas.core.loadbalance;

import com.github.pampas.common.loadbalance.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 负载均衡器工厂类
 * Created by darrenfu on 17-6-28.
 */
public class LoadBalanceFactory {
    private static final Logger log = LoggerFactory.getLogger(LoadBalanceFactory.class);

    private static final ConcurrentHashMap<String, LoadBalancer> serviceLoadBalancerMap = new ConcurrentHashMap<>();


    /**
     * 按照service提供LB
     *
     * @param serviceName
     * @param loadBalancerClz
     * @return
     */
    public static LoadBalancer createLoadBalancerForService(String serviceName, Class<? extends LoadBalancer> loadBalancerClz) {

        LoadBalancer balancer = serviceLoadBalancerMap.get(serviceName);
        if (balancer == null) {
            LoadBalancer newLoadBalancer = createLoadBalancer(loadBalancerClz);
            serviceLoadBalancerMap.putIfAbsent(serviceName, newLoadBalancer);
            balancer = serviceLoadBalancerMap.get(serviceName);
        }

        return balancer;
    }

    private static LoadBalancer createLoadBalancer(Class<? extends LoadBalancer> loadBalancer) {
        if (RandomLoadBalancer.class.equals(loadBalancer)) {
            return createRandomLoadBalancer();
        } else if (RoundRobinLoadBalancer.class.equals(loadBalancer)) {
            return createRoundRobinBalancer();
        } else {
            try {
                return loadBalancer.newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassCastException e) {
                log.error("无法创建指定的负载均衡器:{},ex:{} ", loadBalancer, e.getMessage(), e);
            }
        }
        return createRoundRobinBalancer();
    }

    /**
     * Create random load balancer load balancer.
     *
     * @return the load balancer
     */
    public static LoadBalancer createRandomLoadBalancer() {
        return new RandomLoadBalancer();
    }

    public static LoadBalancer createRoundRobinBalancer() {
        return new RoundRobinLoadBalancer();
    }


}
