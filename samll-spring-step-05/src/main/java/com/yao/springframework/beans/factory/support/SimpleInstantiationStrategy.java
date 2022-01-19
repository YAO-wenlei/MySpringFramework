package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanExcepiton;
import com.yao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 15:18:05
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object... args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null == constructor) {
                return clazz.getDeclaredConstructor().newInstance();
            } else {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
        } catch (NoSuchMethodException | InvocationTargetException |IllegalAccessException | InstantiationException e){
            throw new BeanExcepiton("bean instance exception" + clazz.getName());
        }
    }
}
