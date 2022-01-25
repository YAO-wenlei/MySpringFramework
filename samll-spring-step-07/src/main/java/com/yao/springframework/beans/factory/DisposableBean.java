package com.yao.springframework.beans.factory;

public interface DisposableBean {
    void destroy() throws Exception;
}
