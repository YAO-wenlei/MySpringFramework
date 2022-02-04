package com.yao.springframework.context.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValues;
import com.yao.springframework.beans.factory.BeanFactory;
import com.yao.springframework.beans.factory.BeanFactoryAware;
import com.yao.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yao.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.yao.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yaowenlei
 * @description
 * @date 2022年02月04日 11:04 上午
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private ConfigurableListableBeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Class<?> beanClass, String beanName) throws BeanException {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
        //1.处理Value的值
        Class<?> aClass = bean.getClass();
        aClass = ClassUtils.isCglibProxyClass(aClass) ? aClass.getSuperclass() : aClass;

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Value annotation = declaredField.getAnnotation(Value.class);
            if (null != annotation) {
                String value = annotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean,declaredField.getName(),value);
            }
        }

        //2.处理AutoWired
        for (Field declaredField : declaredFields) {
            Autowired autowiredAnnotation = declaredField.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> type = declaredField.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = declaredField.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, type);
                }else {
                    dependentBean = beanFactory.getBean(type);
                }
                BeanUtil.setFieldValue(bean,declaredField.getName(),dependentBean);
            }
        }
        return pvs;
    }
}
