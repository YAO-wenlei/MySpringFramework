package com.yao.springframework.beans;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 09:51:10
 */
public class BeanExcepiton extends RuntimeException{
    public BeanExcepiton(String msg) {
        super(msg);
    }

    public BeanExcepiton(String msg, Throwable e) {
        super(msg,e);
    }
}
