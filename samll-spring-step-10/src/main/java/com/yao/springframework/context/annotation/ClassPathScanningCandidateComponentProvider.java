package com.yao.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.yao.springframework.beans.factory.config.BeanDefinition;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author yaowenlei
 * @description 扫描指定包下 标记指定注解的类
 * @date 2022年02月03日 2:06 下午
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidate = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> aClass : classes) {
            candidate.add(new BeanDefinition(aClass));
        }
        return candidate;
    }

}
