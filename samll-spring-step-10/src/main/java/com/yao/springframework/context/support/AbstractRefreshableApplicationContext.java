package com.yao.springframework.context.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yao.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月21日 11:31 下午
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Override
    protected void refreshBeanFactory() throws BeanException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.defaultListableBeanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return this.defaultListableBeanFactory;
    }
}
