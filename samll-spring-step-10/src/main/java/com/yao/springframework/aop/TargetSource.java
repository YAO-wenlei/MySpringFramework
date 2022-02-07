package com.yao.springframework.aop;

import com.yao.springframework.util.ClassUtils;

/**
 * @author yaowenlei
 * @description 被代理对象
 * @date 2022年01月30日 1:54 下午
 */
public class TargetSource {
    private Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        Class<?> aClass = this.target.getClass();
        aClass = ClassUtils.isCglibProxyClass(aClass) ? aClass.getSuperclass() : aClass;
        return aClass.getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
