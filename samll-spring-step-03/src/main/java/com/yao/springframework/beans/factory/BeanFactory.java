package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-14 16:42:07
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeanException;

    Object getBean(String beanName, Object... args) throws BeanException;
}
