package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegister {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
