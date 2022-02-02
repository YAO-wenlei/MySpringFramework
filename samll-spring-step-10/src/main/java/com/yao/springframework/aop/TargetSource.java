package com.yao.springframework.aop;

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
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
