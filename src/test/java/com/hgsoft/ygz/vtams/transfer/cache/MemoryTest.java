package com.hgsoft.ygz.vtams.transfer.cache;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 内存占用测试
 *
 * @author 赖少涯
 * @date 2017-10-17
 */

public class MemoryTest {

    private static final Logger log = LoggerFactory.getLogger(MemoryTest.class);

    @Test
    public void testMemory() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Runtime runtime = Runtime.getRuntime();

        //空闲内存
        long freeMemory = runtime.freeMemory();
        //内存总量
        long totalMemory = runtime.totalMemory();
        //最大允许使用的内存
        long maxMemory = runtime.maxMemory();

        System.out.println("freeMemory=" + freeMemory / 1024 / 1024);
        System.out.println("totalMemory=" + totalMemory / 1024 / 1024);
        System.out.println("maxMemory=" + maxMemory / 1024 / 1024);
        System.out.println("时间戳:" + new Timestamp(System.currentTimeMillis()));
        System.out.println("时间戳:" + new Timestamp(new Date().getTime()));
        System.out.println(String.valueOf(new Date().getTime()).length());
    }
}
