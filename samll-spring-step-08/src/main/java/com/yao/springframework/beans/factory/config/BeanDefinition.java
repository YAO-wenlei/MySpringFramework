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
    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;
    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private Class beanClass;
    private PropertyValues propertyValues;

    private String initMethodName;
    private String destroyMethodName;

    private String scope = SCOPE_SINGLETON;
    private boolean singleton = true;
    private boolean prototype = false;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    /**
     * @param beanClass
     * @param propertyValues
     * @return null
     * @author yaowenlei
     * @description
     * @date 2022/1/19 10:54 下午
     **/
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
