package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.ObjectFactory;
import com.yao.springframework.beans.factory.DisposableBean;
import com.yao.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 13:54:31
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    protected static final Object NULL_OBJECT = new Object();

    //一级缓存 用于存放完整的bean
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    //二级缓存 提前暴露对象 没有完全实例化的对象
    protected final Map<String, Object> earlySingletonObjects = new HashMap<>();

    //三级缓存 存放代理对象
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    //需要销毁的bean
    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    //获取单例的bean
    public Object getSingleton(String beanName) {
        //从一级缓存里拿
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            //从二级缓存里拿 这个对象就是代理对象，因为只有代理对象才会放到三级缓存中
            if (null == singletonObject) {
                ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
                if (null != objectFactory) {
                     singletonObject = objectFactory.getObject();
                     //把三级缓存中的真实对象取出来 ,放入二级缓存
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    //添加单例的bean
    public void addSingletonBean(String beanName, Object singletonObject) {
        //向一级缓存中添加对象
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    //添加需要销毁的bean
    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeanMap.put(beanName, disposableBean);
    }

    public void destroySingletons() {
        Set<String> keySet = disposableBeanMap.keySet();
        Object[] destroyBeanName = keySet.toArray();

        for (int i = destroyBeanName.length - 1; i >= 0; i--) {
            Object beanName = destroyBeanName[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanException("Destroy method on bean with name" + beanName, e);
            }
        }
    }


}
