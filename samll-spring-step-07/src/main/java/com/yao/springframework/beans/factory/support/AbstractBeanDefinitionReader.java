package com.yao.springframework.beans.factory.support;

import com.yao.springframework.core.io.DefaultResourceLoader;
import com.yao.springframework.core.io.ResourceLoader;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 10:54 上午
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegister beanDefinitionRegister;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegister beanDefinitionRegister) {
        this(new DefaultResourceLoader(), beanDefinitionRegister);
    }

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader, BeanDefinitionRegister beanDefinitionRegister){
        this.resourceLoader = resourceLoader;
        this.beanDefinitionRegister = beanDefinitionRegister;
    }
    @Override
    public BeanDefinitionRegister getRegister() {
        return this.beanDefinitionRegister;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
