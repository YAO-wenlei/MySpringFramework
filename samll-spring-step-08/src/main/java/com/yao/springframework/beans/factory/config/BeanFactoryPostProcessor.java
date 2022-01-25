package com.yao.springframework.beans.factory.config;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    /**
     *
     * @author yaowenlei
     * @description 给beanDefinition加载之后，bean实例化之前，提供修改beanDefinition的属性的机制
     * @date 2022/1/21 3:39 下午
     * @param configurableListableBeanFactory
    **/
    void postProcessorBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)throws BeanException;
}
