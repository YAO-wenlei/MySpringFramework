package com.yao.springframework.beans.factory.config;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValues;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeanException;
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException;

}
