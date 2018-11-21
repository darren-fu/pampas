package com.github.pampas.storage.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * druid数据源配置
 * Created by erhu on 2017/1/24.
 */
@Configuration
@Profile("!unit_test")
@ConditionalOnProperty(name = "pampas.db.driverClassName")
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    public DataSource dataSource(@Value("${pampas.db.driverClassName}") String driverClassName,
                                 @Value("${pampas.db.url}") String url,
                                 @Value("${pampas.db.username}") String username,
                                 @Value("${pampas.db.password}") String password,
                                 @Value("${druid.pool.size.max:200}") int max,
                                 @Value("${druid.pool.size.min:1}") int min,
                                 @Value("${druid.pool.size.init:1}") int init) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(init);
        druidDataSource.setMaxActive(max);
        druidDataSource.setMinIdle(min);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(120000);
        druidDataSource.setValidationQuery("select 1");
        try {
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            log.error("初始化数据源失败:{}", e);
        }

        return druidDataSource;
    }


//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//    }
}
