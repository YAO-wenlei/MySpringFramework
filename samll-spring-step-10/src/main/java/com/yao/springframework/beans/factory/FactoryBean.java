package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;

public interface FactoryBean<T> {
    T getObject() throws BeanException;

    Class<?> getObjectType();

    boolean isSingleton();
}
