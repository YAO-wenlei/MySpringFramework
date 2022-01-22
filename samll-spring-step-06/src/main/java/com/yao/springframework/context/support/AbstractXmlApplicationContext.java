package com.yao.springframework.context.support;

import com.yao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yao.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月22日 11:47 上午
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionLoader = new XmlBeanDefinitionReader(this, beanFactory);
        String[] configLocations =  getConfigLocations();
        if (null!=configLocations){
            xmlBeanDefinitionLoader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
