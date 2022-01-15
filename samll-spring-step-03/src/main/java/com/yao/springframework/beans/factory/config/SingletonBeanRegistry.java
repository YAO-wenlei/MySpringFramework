package com.yao.springframework.beans.factory.config;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-14 16:18:10
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
