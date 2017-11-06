package com.hgsoft.ygz.vtams.transfer.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.net.URL;

/**
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-04 17:00:56
 * @since 1.7
 */
public class EnvironmentTest {

    @Test
    public void testA() {
        URL url = EnvironmentTest.class.getClassLoader().getResource("");
        System.out.println(url.getPath());
        System.out.println(StringUtils.join(url.getPath().split("/"), ","));
        String[] strings = url.getPath().split("/");
        System.out.println(strings[strings.length - 3]);
    }
}
