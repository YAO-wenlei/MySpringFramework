package com.yao.springframework.beans.factory.config;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException;

    boolean postProcessAfterInstantiation(Object object, String beanName) throws BeanException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException;

    default Object getEarlyBeanReference(Object exposedObject, String beanName){
        return exposedObject;
    };
}
