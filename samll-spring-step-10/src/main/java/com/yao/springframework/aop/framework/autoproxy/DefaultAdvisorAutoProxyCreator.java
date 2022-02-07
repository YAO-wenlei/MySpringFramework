package com.yao.springframework.aop.framework.autoproxy;

import com.yao.springframework.aop.AdvisedSupport;
import com.yao.springframework.aop.Advisor;
import com.yao.springframework.aop.Aspectj.AspectjExpressionPointCutAdvisor;
import com.yao.springframework.aop.Aspectj.AspectjExpressionPointcut;
import com.yao.springframework.aop.ClassFilter;
import com.yao.springframework.aop.TargetSource;
import com.yao.springframework.aop.framework.ProxyFactory;
import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValues;
import com.yao.springframework.beans.factory.BeanFactory;
import com.yao.springframework.beans.factory.BeanFactoryAware;
import com.yao.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.yao.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.reflect.Pointcut;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yaowenlei
 * @description
 * @date 2022年02月02日 10:31 上午
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;
    private final Set<Object> earlyProxyReferences = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object object, String beanName) throws BeanException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
        return pvs;
    }

    private boolean inInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        if (!earlyProxyReferences.contains(beanName)) {
            return wrapIfNecessary(bean, beanName);
        }
        return bean;
    }

    protected Object wrapIfNecessary(Object bean, String beanName) {
        if (inInfrastructureClass(bean.getClass())) return null;

        Collection<AspectjExpressionPointCutAdvisor> advisors = beanFactory.getBeanOfType(AspectjExpressionPointCutAdvisor.class).values();
        for (AspectjExpressionPointCutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointCut().getClassFilter();
            if (!classFilter.matches(bean.getClass())) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(bean.getClass().getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }

            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointCut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getAopProxy();
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReferences.add(beanName);
        return wrapIfNecessary(bean,beanName);
    }
}
