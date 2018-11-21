package com.github.pampas.storage;

import com.github.pampas.storage.config.DataSourceConfig;
import com.github.pampas.storage.config.MybatisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.function.Consumer;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-11-15
 */
@ComponentScan(
        basePackages = {"com.github.pampas.storage"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class)})
@Import(value = {DataSourceConfig.class,
        MybatisConfig.class,
        JacksonAutoConfiguration.class,
        PersistenceExceptionTranslationAutoConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class,
        TransactionAutoConfiguration.class,
})
public class SpringStorageApp implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SpringStorageApp.class);

    public static ApplicationContext applicationContext;


    public static void init(String[] args, Consumer listener) {
        long start = System.currentTimeMillis();

        SpringApplication springApplication = new SpringApplication(SpringStorageApp.class);
        springApplication.addListeners(new ApplicationListener<ContextRefreshedEvent>() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
                log.info("网关存储层启动完成");
                if (listener != null) {
                    listener.accept(applicationContext);
                }
            }
        });
        springApplication.run(args);
    }


    public static void main(String[] args) {
        init(args, null);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
