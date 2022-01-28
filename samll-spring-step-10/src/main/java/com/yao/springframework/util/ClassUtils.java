package com.yao.springframework.util;

import com.yao.springframework.beans.BeanException;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月19日 11:14 下午
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            throw new BeanException("not create classloader");
        }
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }

        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String className){
        return (className != null && className.contains("$$"));
    };
}
