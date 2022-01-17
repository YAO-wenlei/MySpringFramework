package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistory {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
