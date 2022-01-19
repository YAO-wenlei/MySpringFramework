package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanExcepiton;
import com.yao.springframework.beans.factory.BeanFacroty;
import com.yao.springframework.beans.factory.config.BeanDefinition;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 14:02:42
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFacroty {
    @Override
    public Object getBean(String beanName) throws BeanExcepiton {
        return doGetBean(beanName, null);
    }
    @Override
    public Object getBean(String beanName, Object... args) throws BeanExcepiton{
        return doGetBean(beanName, args);
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
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanExcepiton;

    //创建bean
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args)throws BeanExcepiton;
}
