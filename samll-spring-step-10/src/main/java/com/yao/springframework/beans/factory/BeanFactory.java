package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 09:46:59
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeanException;

    Object getBean(String beanName, Object... args) throws BeanException;

    <T> T getBean(String beanName, Class<T> requiredType) throws BeanException;

    <T> T getBean(Class<T> requiredType) throws BeanException;
}
