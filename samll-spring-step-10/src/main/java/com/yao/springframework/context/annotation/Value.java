package com.yao.springframework.context.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
