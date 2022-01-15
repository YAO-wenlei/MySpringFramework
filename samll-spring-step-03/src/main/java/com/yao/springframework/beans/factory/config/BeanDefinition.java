package com.yao.springframework.beans.factory.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-14 16:13:58
 */
@Getter
@Setter
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
}
