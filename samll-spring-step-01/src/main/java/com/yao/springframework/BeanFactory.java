package com.yao.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-14 14:40:53
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String beanName){
        return beanDefinitionMap.get(beanName).getBean();
    }

    public void registerBeanDefinition(String BeanName,BeanDefinition beanDefinition){
        beanDefinitionMap.put(BeanName, beanDefinition);
    }

}
