package com.yao.springframework.aop;

import lombok.Getter;
import lombok.Setter;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author yaowenlei
 * @description 主要是用于把代理、拦截、匹配的各项属性包装到一个类中，方 便在 Proxy 实现类进行使用。
 * @date 2022年01月30日 1:50 下午
 */
@Getter
@Setter
public class AdvisedSupport {
    private boolean proxyTargetClass = false;
    //被代理的目标对象
    private TargetSource targetSource;

    //方法拦截器
    private MethodInterceptor methodInterceptor;

    //方法匹配器
    private MethodMatcher methodMatcher;

}
