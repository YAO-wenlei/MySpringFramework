package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 13:54:31
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
    private Map<String, Object> singletonMap = new HashMap<>();

    //获取单例的bean
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    //添加单例的bean
    protected void addSingletonBean(String beanName, Object singletionObject) {
        singletonMap.put(beanName, singletionObject);
    }
}
