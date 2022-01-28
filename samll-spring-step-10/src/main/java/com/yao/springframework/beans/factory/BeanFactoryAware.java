package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;

/**
 * @author yaowenlei
 * @description 使bean可以感受到所属的beanFactory
 * @date 2022年01月25日 3:31 下午
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeanException;
}
