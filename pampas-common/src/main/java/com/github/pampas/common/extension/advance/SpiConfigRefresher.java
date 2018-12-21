package com.github.pampas.common.extension.advance;

import com.github.pampas.common.extension.Scope;
import com.github.pampas.common.extension.Spi;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-20
 */
@Spi(scope = Scope.SINGLETON, desc = "SPI配置刷新")
public interface SpiConfigRefresher {

    void refreshSpiConfig();
}
