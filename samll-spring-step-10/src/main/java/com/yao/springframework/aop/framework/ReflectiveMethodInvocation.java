package com.yao.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月30日 2:30 下午
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    //目标对象
    protected final Object target;

    //目标方法
    protected final Method method;

    //入参
    protected final Object[] argument;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] argument) {
        this.target = target;
        this.method = method;
        this.argument = argument;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return argument;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target,argument);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
