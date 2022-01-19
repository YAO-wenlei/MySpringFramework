package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanExcepiton;
import com.yao.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 14:44:23
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegister {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanExcepiton {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (null==beanDefinition) throw new BeanExcepiton("No bean name" + beanName + "is defined");
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
