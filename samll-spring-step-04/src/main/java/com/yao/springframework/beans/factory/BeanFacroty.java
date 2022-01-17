package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanExcepiton;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 09:46:59
 */
public interface BeanFacroty {
    Object getBean(String beanName) throws BeanExcepiton;

    Object getBean(String beanName, Object... args) throws BeanExcepiton;
}
