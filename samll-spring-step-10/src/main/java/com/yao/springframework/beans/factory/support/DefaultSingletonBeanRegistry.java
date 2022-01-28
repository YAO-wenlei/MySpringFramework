package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.DisposableBean;
import com.yao.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 13:54:31
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
    private Map<String, Object> singletonMap = new HashMap<>();

    protected static final Object NULL_OBJECT = new Object();

    //需要销毁的bean
    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    //获取单例的bean
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    //添加单例的bean
    public void addSingletonBean(String beanName, Object singletonObject) {
        singletonMap.put(beanName, singletonObject);
    }

    //添加需要销毁的bean
    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeanMap.put(beanName, disposableBean);
    }

    public void destroySingletons() {
        Set<String> keySet = disposableBeanMap.keySet();
        Object[] destroyBeanName = keySet.toArray();

        for (int i = destroyBeanName.length-1; i >=0 ; i--) {
            Object beanName = destroyBeanName[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanException("Destroy method on bean with name" + beanName,e);
            }
        }
    }
}
