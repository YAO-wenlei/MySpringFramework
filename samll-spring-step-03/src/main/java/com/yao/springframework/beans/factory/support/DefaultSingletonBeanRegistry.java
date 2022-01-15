package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-14 16:34:40
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    //维护单例的bean
    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    //添加单例的bean
    protected void addSingletonBean(String beanName, Object singleObject) {
        singletonObjects.put(beanName, singleObject);
    }
}
