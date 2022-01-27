package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.config.BeanPostProcessor;
import com.yao.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 5:58 下午
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory ,ListableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    void proInstantiateSingletons() throws BeanException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
