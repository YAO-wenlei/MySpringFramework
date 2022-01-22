package com.yao.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValue;
import com.yao.springframework.beans.PropertyValues;
import com.yao.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.config.BeanPostProcessor;
import com.yao.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 14:27:15
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            //创建实例
            bean = createBeanInstance(beanName, beanDefinition, args);
            //属性填充
            applyPropertyValue(beanName, bean, beanDefinition);
            //执行bean的初始化方法 和beanProcessor方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeanException("bean instance exception", e);
        }
        addSingletonBean(beanName, bean);
        return bean;
    }


    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructorToUse = null;
        Constructor[] declaredConstructors = beanDefinition.getBeanClass().getDeclaredConstructors();

        for (Constructor declaredConstructor : declaredConstructors) {
            if (args != null && declaredConstructor.getParameterTypes().length == args.length) {
                constructorToUse = declaredConstructor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }


    protected void applyPropertyValue(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                //判断属性是否是引用类型
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("beanApplyProperty error", e);
        }

    }

    ;

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        Object wrapperBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        //执行invokeInitMethods方法
        invokeInitMethod(beanName, bean, beanDefinition);

        wrapperBean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        return wrapperBean;
    }

    private void invokeInitMethod(String beanName, Object bean, BeanDefinition beanDefinition) {

    }


    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        //遍历所有的beanPostProcess
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }
}
