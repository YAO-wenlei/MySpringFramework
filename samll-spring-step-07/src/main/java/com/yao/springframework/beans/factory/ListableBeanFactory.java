package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    /**
     * @param type
     * @return java.util.Map<java.lang.String, T>
     * @author ywl
     * @description 按照类型返回Bean实例
     * @date 2022/1/20 6:01 下午
     **/
    <T> Map<String, T> getBeanOfType(Class<T> type) throws BeanException;

    /**
     * @return java.lang.String[]
     * @author yaowenlei
     * @description 返回所有bean的名称
     * @date 2022/1/20 6:02 下午
     **/
    String[] getBeanDefinitionNames();
}
