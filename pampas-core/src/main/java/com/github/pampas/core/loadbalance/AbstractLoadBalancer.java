package com.github.pampas.core.loadbalance;

import com.github.pampas.common.discover.ServerContext;
import com.github.pampas.common.discover.ServerInstance;
import com.github.pampas.common.loadbalance.LoadBalancer;
import com.github.pampas.common.route.Locator;
import com.github.pampas.common.tools.CommonTools;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负载均衡器，顶级抽象类
 * Created by darrenfu on 17-7-31.
 */
public abstract class AbstractLoadBalancer implements LoadBalancer {
    private static Logger log = LoggerFactory.getLogger(AbstractLoadBalancer.class);

    private AtomicInteger alarmCount = new AtomicInteger();

    @Override
    public ServerInstance choose(ServerContext context, Locator locator, Set<String> excludeInstanceIdSet) {

        if (context == null || locator == null || StringUtils.isEmpty(locator.getServiceName())) {
            return null;
        }
        List<ServerInstance> serverList = context.getServerList(locator.getServiceName());

        if (serverList == null || serverList.size() == 0) {
            log.warn("没有获取服务实例:{}", locator.getServiceName());
            return null;
        }
        List<ServerInstance> usableServerList = new LinkedList();

        //移除未初始化完成或当前状态未存活,版本不匹配，已排除的实例
        for (ServerInstance instance : serverList) {
            if (instance.isReady()
                    && instance.getIsAlive()
//                    && isVersionOk(command, instance)
                    && !shouldExcludeInstance(excludeInstanceIdSet, instance)) {

                usableServerList.add(instance);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("剔除本次不可使用的server实例:{}", instance);
                }
                if (alarmCount.getAndIncrement() < 500) {
                    log.warn("剔除本次不可使用的server实例:{},isReady:{},isAlive:{},isVersionOk:{},shouldExcludeInstance{},",
                            instance, instance.isReady(),
                            instance.getIsAlive(),
//                            isVersionOk(command, instance),
                            shouldExcludeInstance(excludeInstanceIdSet, instance));
                }

            }
        }

        if (CommonTools.isEmpty(usableServerList)) {
            log.warn("没有获取可用服务实例:{}", locator.getServiceName());
            if (alarmCount.getAndIncrement() < 500) {
                log.warn("版本匹配失败：serverList:{},excludeInstanceIdSet:{},locator:{}", ArrayUtils.toString(serverList), excludeInstanceIdSet, locator);
            }
            return null;
        }

        //只有一个实例，直接返回，忽视排除的实例ID
        if (usableServerList.size() == 1) {
            return usableServerList.get(0);
        }
        return doChoose(usableServerList, locator);
    }

//    /**
//     * 验证版本是否OK
//     *
//     * @param command  the command
//     * @param instance the instance
//     * @return boolean boolean
//     */
//    protected boolean isVersionOk(RestyCommand command, ServerInstance instance) {
//        List<VersionRule> versionRules = command.getRestyCommandConfig().getVersion();
//        if (versionRules == null || versionRules.size() == 0) {
//            return true;
//        }
//
//        for (VersionRule versionRule : versionRules) {
//            //版本不匹配版本规则
//            if (!versionRule.match(instance.getVersionInfo())) {
//                if (alarmCount.getAndIncrement() < 500) {
//                    log.warn("版本匹配失败：versionRule{},instance.getVersionInfo():{}", versionRule, instance.getVersionInfo());
//                }
//                return false;
//            }
//        }
//        // 匹配成功
//        return true;
//    }

    /**
     * 判断instance是否已被排除
     *
     * @param excludeInstanceIdSet
     * @param instance
     * @return
     */
    private boolean shouldExcludeInstance(Set<String> excludeInstanceIdSet, ServerInstance instance) {
        return CommonTools.isNotEmpty(excludeInstanceIdSet) && excludeInstanceIdSet.contains(instance.getInstanceId());
    }

    /**
     * 基于特定算法，选举可用server实例
     *
     * @param instanceList the instance list
     * @param locator      the locator
     * @return the server instance
     */
    protected abstract ServerInstance doChoose(List<ServerInstance> instanceList, Locator locator);

    /**
     * 计算权重
     * copy from dubbo
     *
     * @param serverInstance the server instance
     * @return weight weight
     */
    int getWeight(ServerInstance serverInstance) {
        int weight = serverInstance.getWeight();

        if (weight > 0) {
            long timestamp = serverInstance.getStartTimestamp();
            if (timestamp > 0L) {
                int uptime = (int) (System.currentTimeMillis() - timestamp);
                int warmup = serverInstance.getWarmupSeconds() * 1000;

                if (uptime > 0 && uptime < warmup) {
                    weight = calculateWarmupWeight(uptime, warmup, weight);
                }
            }
        }

        return weight;
    }

    /**
     * 根据启动时间和预热时间计算权重
     *
     * @param uptime 已经启动的时间 ms
     * @param warmup 预热时间 ms
     * @param weight 权重
     * @return the int
     */
    private int calculateWarmupWeight(int uptime, int warmup, int weight) {
        int ww = (int) ((float) uptime / ((float) warmup / (float) weight));
        return ww < 1 ? 1 : (ww > weight ? weight : ww);
    }

}
