package com.yao.springframework.beans;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-15 13:02:28
 */
public class BeanException extends RuntimeException {
    public BeanException(String msg) {
        super(msg);
    }

    public BeanException(String msg, Throwable e) {
        super(msg, e);
    }
}
