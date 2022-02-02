package com.yao.springframework.aop;

import sun.tools.java.ClassFile;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月28日 5:16 下午
 */
public interface PointCut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
