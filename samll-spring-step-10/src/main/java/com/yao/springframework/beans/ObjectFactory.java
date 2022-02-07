package com.yao.springframework.beans;

public interface ObjectFactory<T> {
    T getObject() throws BeanException;
}
