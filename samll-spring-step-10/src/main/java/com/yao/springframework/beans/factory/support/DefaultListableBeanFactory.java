package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yao.springframework.beans.factory.config.BeanDefinition;

import java.util.*;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 14:44:23
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegister, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (null == beanDefinition) throw new BeanException("No bean name" + beanName + "is defined");
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeanException {
        HashMap<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public void proInstantiateSingletons() throws BeanException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeanException {
        List<String> beanNames = new ArrayList<>();
        Set<Map.Entry<String, BeanDefinition>> entries = beanDefinitionMap.entrySet();
        for (Map.Entry<String, BeanDefinition> entry : entries) {
            if (requiredType.isAssignableFrom(entry.getValue().getBeanClass())) {
                beanNames.add(entry.getKey());
            }
        }

        if (1 == beanNames.size()){
            return getBean(beanNames.get(0), requiredType);
        }

        throw new BeanException(requiredType + "expected single bean but fond" + beanNames.size() + ":" + beanNames);
    }
}
