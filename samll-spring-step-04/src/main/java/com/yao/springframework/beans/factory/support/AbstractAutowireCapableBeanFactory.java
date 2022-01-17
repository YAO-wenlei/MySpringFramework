package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanExcepiton;
import com.yao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 14:27:15
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanExcepiton {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        }catch (Exception e ){
            throw new BeanExcepiton("bean instance exception", e);
        }
        addSingletonBean(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args){
        Constructor constructorToUse = null;
        Constructor[] declaredConstructors = beanDefinition.getBeanClass().getDeclaredConstructors();

        for (Constructor declaredConstructor : declaredConstructors) {
            if (args != null && declaredConstructor.getParameterTypes().length == args.length){
                constructorToUse = declaredConstructor;
                break;
            }
        }
        return  instantiationStrategy.instantiate(beanDefinition, beanName,constructorToUse, args);
    }
}
