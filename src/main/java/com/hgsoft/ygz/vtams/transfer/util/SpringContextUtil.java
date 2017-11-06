package com.hgsoft.ygz.vtams.transfer.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * spring 上下文工具类
 *
 * @author 赖少涯
 * @date 2017-10-10
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static Map<String, Object> beanMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }


    /**
     * 根据bean name获取bean <br/>
     * 由于并发调用，这里可能会出现重复赋值的现象，但是由于在此处对性能的影响较小且对结果无影响，所以不作处理
     *
     * @param name bean name
     * @return 如果找到了则返回bean，否则返回null
     */
    public static Object getBean(String name) {
        if (beanMap.containsKey(name)) {
            return beanMap.get(name);
        }

        try {
            Object object = applicationContext.getBean(name);
            beanMap.put(name, object);
            return object;
        } catch (BeansException e) {
            return null;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
