package com.yao.springframework;

/**
 * @author YAO_WENLEI
 * @description: bean定义信息
 * @since 2022-01-14 14:40:37
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean(){
        return bean;
    }
}
