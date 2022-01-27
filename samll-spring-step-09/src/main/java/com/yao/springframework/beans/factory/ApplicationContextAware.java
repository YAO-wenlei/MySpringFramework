package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware{
    void setApplicationContext(ApplicationContext applicationContext) throws BeanException;
}
