package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.config.BeanDefinition;

/**
 * @author YAO_WENLEI
 * @description: 主要负责创建bean
 * @since 2022-01-15 13:34:24
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException("bean newInstance exception", e);
        }
        addSingletonBean(beanName, bean);
        return bean;
    }
}
