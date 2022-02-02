package com.yao.springframework.aop;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月28日 5:12 下午
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
