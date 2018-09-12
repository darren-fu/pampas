package com.github.pampas.core.loadbalance;

import com.github.pampas.common.base.PampasConsts;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.extension.SpiMeta;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.tools.AtomicPositiveInteger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 轮询调度
 * 负载均衡
 * copy from dubbo
 * Created by darrenfu on 17-7-30.
 */
@SpiMeta(name = PampasConsts.LoadBalancer.ROUND_ROBIN)
public class RoundRobinLoadBalancer extends AbstractLoadBalancer {

    private final ConcurrentHashMap<String, AtomicPositiveInteger> sequences = new ConcurrentHashMap<>();

    @Override
    protected ServerInstance doChoose(List<ServerInstance> instanceList, Locator locator) {
        String key = locator.getServiceName() + "." + locator.getMappedPath();
        int length = instanceList.size(); // 总个数
        int maxWeight = 0; // 最大权重
        int minWeight = Integer.MAX_VALUE; // 最小权重
        final LinkedHashMap<ServerInstance, IntegerWrapper> invokerToWeightMap = new LinkedHashMap();
        int weightSum = 0;
        for (int i = 0; i < length; i++) {
            int weight = getWeight(instanceList.get(i));
            maxWeight = Math.max(maxWeight, weight); // 累计最大权重
            minWeight = Math.min(minWeight, weight); // 累计最小权重
            if (weight > 0) {
                invokerToWeightMap.put(instanceList.get(i), new IntegerWrapper(weight));
                weightSum += weight;
            }
        }
        AtomicPositiveInteger sequence = sequences.get(key);
        if (sequence == null) {
            sequences.putIfAbsent(key, new AtomicPositiveInteger());
            sequence = sequences.get(key);
        }
        int currentSequence = sequence.getAndIncrement();
        if (maxWeight > 0 && minWeight < maxWeight) { // 权重不一样
            int mod = currentSequence % weightSum;
            for (int i = 0; i < maxWeight; i++) {
                for (Map.Entry<ServerInstance, IntegerWrapper> each : invokerToWeightMap.entrySet()) {
                    final ServerInstance instance = each.getKey();
                    final IntegerWrapper weight = each.getValue();
                    if (mod == 0 && weight.getValue() > 0) {
                        return instance;
                    }
                    if (weight.getValue() > 0) {
                        weight.decrement();
                        mod--;
                    }
                }
            }
        }
        // 取模轮循
        return instanceList.get(currentSequence % length);
    }

    private static final class IntegerWrapper {
        public IntegerWrapper(int value) {
            this.value = value;
        }

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void decrement() {
            this.value--;
        }
    }
}
