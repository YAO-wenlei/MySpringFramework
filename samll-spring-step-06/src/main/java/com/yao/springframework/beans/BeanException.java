package com.yao.springframework.beans;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 09:51:10
 */
public class BeanException extends RuntimeException{
    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String msg, Throwable e) {
        super(msg,e);
    }
}
