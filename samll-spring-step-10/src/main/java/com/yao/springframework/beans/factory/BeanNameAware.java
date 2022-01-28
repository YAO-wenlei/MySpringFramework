package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月25日 3:40 下午
 */
public interface BeanNameAware extends Aware {
    void setBeanName(String beanName) throws BeanException;
}
