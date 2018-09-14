package com.github.pampas.common.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-14
 */
public class TaskTools {

    private static final Logger log = LoggerFactory.getLogger(TaskTools.class);

    public static void scheduledTask(String taskName, long intervalMilliseconds, Supplier supplier) {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(() -> {
            try {
                supplier.get();
            } catch (Throwable throwable) {
                log.debug("{}调度失败:{}", taskName, throwable.getMessage(), throwable);
            }
        }, 0, intervalMilliseconds, TimeUnit.MILLISECONDS);

    }


}
