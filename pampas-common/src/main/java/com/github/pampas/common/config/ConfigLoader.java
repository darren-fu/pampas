package com.github.pampas.common.config;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-06-06
 */
@Spi(scope = Scope.SINGLETON,desc = "配置加载器")
public interface ConfigLoader<T extends VersionConfig> {

    Class<T> configClass();

    void addListener(Configurable<T> configurable);

//    void markConfigurable(Class<? extends VersionConfig> configClz, Configurable configurable);

    default boolean lazy() {
        return false;
    }

    T loadConfig();

    T refreshConfig();

}
