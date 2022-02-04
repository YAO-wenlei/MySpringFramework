package com.yao.springframework.aop.framework;

import com.yao.springframework.aop.AdvisedSupport;

/**
 * @author yaowenlei
 * @description
 * @date 2022年02月02日 10:09 上午
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getAopProxy() {
        return createAopProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
