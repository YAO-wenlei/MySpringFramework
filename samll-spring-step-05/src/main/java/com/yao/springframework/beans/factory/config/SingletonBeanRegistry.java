package com.yao.springframework.beans.factory.config;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 10:21:26
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
