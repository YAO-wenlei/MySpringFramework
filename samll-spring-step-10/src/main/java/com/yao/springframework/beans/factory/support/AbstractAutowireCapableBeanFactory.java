package com.yao.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValue;
import com.yao.springframework.beans.PropertyValues;
import com.yao.springframework.beans.factory.*;
import com.yao.springframework.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

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
            //判断是否返回代理对象
            bean = resolveBeforeInstantiation(beanName, beanDefinition);
            if (bean != null) {
                return bean;
            }
            //创建实例
            bean = createBeanInstance(beanName, beanDefinition, args);
            //在属性填充前设置bean的属性
            applyBeanPostProcessorBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            //属性填充
            applyPropertyValue(beanName, bean, beanDefinition);
            //执行bean的初始化方法 和beanProcessor方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeanException("bean instance exception", e);
        }

        //注册实现DisposableBean接口的bean
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);

        if (beanDefinition.isSingleton()){
            addSingletonBean(beanName, bean);
        }
        return bean;
    }

    //在属性填充前 通过注解注入属性值
    private void applyBeanPostProcessorBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues propertyValues = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (null != propertyValues) {
                    for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    //是否返回代理对象
    private Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorBeforeInitialization(beanDefinition.getBeanClass(), beanName);
        if (bean != null){
            bean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        }
        return bean;
    }

    public Object applyBeanPostProcessorBeforeInitialization(Class<?> beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            //如果继承此就接口 就说明是一个代理类
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInitialization(beanClass, beanName);
                if (null != result) return result;
            }
        }
        return null;
    }


    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (!beanDefinition.isSingleton()) return;
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
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


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware)bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }


        Object wrapperBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        //执行invokeInitMethods方法
        try {
            invokeInitMethod(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeanException("invokeInitMethod of bean [" + beanName + "] fail",e);
        }

        wrapperBean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        return wrapperBean;
    }

    private void invokeInitMethod(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        //实现InitializingBean接口
        if (bean instanceof InitializingBean){
            ((InitializingBean)bean).afterPropertiesSet();
        }

        //配置xml
        String initMethodName = beanDefinition.getInitMethodName();
        Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
        if (StrUtil.isNotEmpty(initMethodName)) {
            if (null == initMethod) {
                throw new BeanException("method is not find" + initMethod);
            }
            initMethod.invoke(bean);
        }
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
