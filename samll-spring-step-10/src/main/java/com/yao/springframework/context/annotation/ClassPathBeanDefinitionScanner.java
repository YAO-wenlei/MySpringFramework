package com.yao.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.support.BeanDefinitionRegister;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * @author yaowenlei
 * @description
 * @date 2022年02月03日 2:15 下午
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegister register;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegister register) {
        this.register = register;
    }

    public void doScan(String... packages) {
        for (String aPackage : packages) {
            Set<BeanDefinition> beanDefinitions = findCandidateComponents(aPackage);
            for (BeanDefinition beanDefinition : beanDefinitions) {
                String scopeName = getScopeName(beanDefinition);
                if (StrUtil.isNotEmpty(scopeName)) {
                    beanDefinition.setScope(scopeName);
                }
                register.registerBeanDefinition(determineName(beanDefinition),beanDefinition);
            }
        }
    }

    //获取bean的名称
    private String determineName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            return StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

    //获取bean的scope
    private String getScopeName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        String value = scope.value();
        if (StrUtil.isNotEmpty(value)) {
            return value;
        }
        return StrUtil.EMPTY;
    }



}
