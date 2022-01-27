package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;

public interface BeanClassLoaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader)throws BeanException;
}
