package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.BeanFactory;
import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.config.BeanPostProcessor;
import com.yao.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 14:02:42
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws BeanException {
        return doGetBean(beanName, null);
    }
    @Override
    public Object getBean(String beanName, Object... args) throws BeanException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeanException {
        return (T) getBean(beanName);
    }

    protected <T> T doGetBean(final String beanName, Object[] args){
        //获取bean 没有创建
        Object singleton = getSingleton(beanName);
        if (null != singleton) {
            return (T) singleton;
        }
        //获取beanDefinition信息 创建bean
        BeanDefinition beandefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beandefinition, args);
    }

    //获取bean的定义信息
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    //创建bean
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args)throws BeanException;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    //返回beanPostProcessorList
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

}
