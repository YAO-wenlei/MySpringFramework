package com.yao.springframework.beans.factory.config;

import com.yao.springframework.beans.BeanException;

public interface BeanPostProcessor {
    /**
     * @param bean
     * @param beanName
     * @return java.lang.Object
     * @author yaowenlei
     * @description 提供bean初始化方法之前，执行此方法
     * @date 2022/1/21 3:53 下午
     **/

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException;
    /**
     * @param bean
     * @param beanName
     * @return java.lang.Object
     * @author yaowenlei
     * @description 提供bean初始化方法之后，执行此方法
     * @date 2022/1/21 3:53 下午
     **/
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException;
}
