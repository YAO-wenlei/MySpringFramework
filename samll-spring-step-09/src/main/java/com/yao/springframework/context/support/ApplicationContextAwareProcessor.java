package com.yao.springframework.context.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.ApplicationContextAware;
import com.yao.springframework.beans.factory.config.BeanPostProcessor;
import com.yao.springframework.context.ApplicationContext;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月25日 4:00 下午
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return null;
    }
}
