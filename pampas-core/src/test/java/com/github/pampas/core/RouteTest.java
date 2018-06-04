package com.github.pampas.core;

import com.github.pampas.common.extension.SpiContext;
import com.github.pampas.common.route.Selector;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-06-04
 */
public class RouteTest {

    @Test
    public void testLoadSelector() {
        SpiContext<Selector> selectorSpiContext = SpiContext.getContext(Selector.class);

        List<Class<Selector>>  selectorClzList = selectorSpiContext.getSpiClasses(null);
        List<String> spiNameList = selectorClzList.stream().map(v -> SpiContext.getSpiName(v)).collect(Collectors.toList());
        for (String string : spiNameList) {
            System.out.println("->" + string);
        }
        System.out.println(spiNameList);
        Assert.assertTrue(spiNameList.size() > 0);
    }
}
