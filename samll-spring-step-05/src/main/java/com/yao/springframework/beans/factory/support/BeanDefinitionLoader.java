package com.yao.springframework.beans.factory.support;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.core.io.Resource;
import com.yao.springframework.core.io.ResourceLoader;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 10:56 上午
 */
public interface BeanDefinitionLoader {
    BeanDefinitionRegister getRegister();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(Resource... resources) throws BeanException;

    void loadBeanDefinitions(String location) throws BeanException;
}
