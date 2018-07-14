package com.github.pampas.common.config;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-06-06
 */
@Spi(scope = Scope.SINGLETON)
public interface ConfigLoader<T extends VersionConfig> {

    void markConfigurable(Class<? extends VersionConfig> configClz, Configurable configurable);

    T loadConfig(Class<T> configClz);

}
