package com.yao.springframework.beans.factory.config;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * @param existingBean
     * @param beanName
     * @return java.lang.Object
     * @author yaowenlei
     * @description 执行bean 的BeanPostProcessorBeforeInitialization方法
     * @date 2022/1/22 1:49 下午
     **/

    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeanException;

    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeanException;
}
