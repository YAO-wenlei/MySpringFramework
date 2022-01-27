package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月25日 10:04 下午
 */
public abstract class FactoryBeanRegisterSupport extends DefaultSingletonBeanRegistry {
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCacheObjectForFactoryBean(String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (object == null) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, (object != null ? object : NULL_OBJECT));
            }
            return (object != null ? object : NULL_OBJECT);
        }else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeanException("FactoryBean threw exception on object[" + beanName + "]creation", e);
        }
    }
}
