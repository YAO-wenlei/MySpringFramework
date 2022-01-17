package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.BeanFactory;
import com.yao.springframework.beans.factory.config.BeanDefinition;

/**
 * @author YAO_WENLEI
 * @description: 抽象工厂 主要负责控制获取 类的流程
 * @since 2022-01-14 16:24:57
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeanException{
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeanException{
        return doGetBean(beanName,args);
    }

    protected<T> T doGetBean(final String beanName,final Object[] args) {
        //获取单例的bean
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return (T)singleton;
        }
        BeanDefinition beanDefinition = getDefinition(beanName);
        return (T)createBean(beanName, beanDefinition,args);
    }

    protected abstract BeanDefinition getDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object... args) throws BeanException;
}
