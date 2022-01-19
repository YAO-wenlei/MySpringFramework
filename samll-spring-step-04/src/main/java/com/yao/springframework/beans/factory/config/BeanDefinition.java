package com.yao.springframework.beans.factory.config;

import com.yao.springframework.beans.PropertyValue;
import com.yao.springframework.beans.PropertyValues;
import lombok.Getter;
import lombok.Setter;
import sun.jvm.hotspot.interpreter.BytecodeNewArray;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 09:49:17
 */
@Getter
@Setter
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
}
