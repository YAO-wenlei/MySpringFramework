package com.yao.springframework.context;

import com.yao.springframework.beans.BeanException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    //刷新容器
    void refresh()throws BeanException;

    void registerShutdownHook();

    void close();
}
