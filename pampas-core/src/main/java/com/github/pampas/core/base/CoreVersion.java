package com.github.pampas.core.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-11-30
 */
public class CoreVersion {

    private static CoreVersion coreVersion = new CoreVersion();
    private String version = "";

    private static final Logger log = LoggerFactory.getLogger(CoreVersion.class);

    private CoreVersion() {
        final Properties properties = new Properties();
        try {
            properties.load(CoreVersion.class.getClassLoader().getResourceAsStream("core.properties"));
        } catch (IOException e) {
            log.error("加载core.properties失败:{}", e.getMessage(), e);
            throw new RuntimeException("启动失败:" + e.getMessage());
        }
        version = properties.getProperty("version");
    }


    public static String getVersion() {
        return coreVersion.version;
    }

}
