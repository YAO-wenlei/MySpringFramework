package com.yao.springframework.beans.factory.config;

import com.yao.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 5:56 下午
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
