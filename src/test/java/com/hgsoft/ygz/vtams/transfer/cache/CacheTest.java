package com.hgsoft.ygz.vtams.transfer.cache;

import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 缓存测试
 *
 * @author 赖少涯
 * @date 2017-10-18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
public class CacheTest {

    @Autowired
    private CacheManager cacheManager;

    private static final String CACHE_POINT_MAPPING = "point_mapping_cache";


    @Test
    public void testPutCache() {
        Runtime runtime = Runtime.getRuntime();
        final long totalMemoryStart = runtime.totalMemory() / 1024 / 1024;
        final long freeMemoryStart = runtime.freeMemory() / 1024 / 1024;
        final long maxMemoryStart = runtime.maxMemory() / 1024 / 1024;
        System.out.println("当前内容总量：" + totalMemoryStart);
        System.out.println("当前最大可用内存总：" + maxMemoryStart);
        System.out.println("当前空闲内存：" + freeMemoryStart);


        Cache cache = cacheManager.getCache(CACHE_POINT_MAPPING);
        PointMapping pointMapping = new PointMapping();
        pointMapping.setOldPointCode("SAWYER003");
        pointMapping.setNewPointCode("4401010100101010001");

        pointMapping.setCreationDate(new Date());
        pointMapping.setAgencyId("44010199999");
        pointMapping.setChannelType("01");

        for (int i = 0; i < 30000; i++) {
            pointMapping.setStatus(i);
            cache.put(i, pointMapping);
            //System.out.println(i + "====>" + cache.get(i).get());
        }

        PointMapping last = cache.get(29999, PointMapping.class);
        if (null != last) {
            System.out.println("获取最后一个对象:" + last);
        }

        System.out.println("======================");
        Runtime runtimeEnd = Runtime.getRuntime();
        final long totalMemoryEnd = runtimeEnd.totalMemory() / 1024 / 1024;
        final long freeMemoryEnd = runtimeEnd.freeMemory() / 1024 / 1024;
        final long maxMemoryEnd = runtimeEnd.maxMemory() / 1024 / 1024;
        System.out.println("内容总量变化：" + totalMemoryStart + " ==> " + totalMemoryEnd);
        System.out.println("最大可用内存变化：" + maxMemoryStart + " ==> " + maxMemoryEnd);
        System.out.println("空闲内存变化：" + freeMemoryStart + " ==> " + freeMemoryEnd);

    }
}
