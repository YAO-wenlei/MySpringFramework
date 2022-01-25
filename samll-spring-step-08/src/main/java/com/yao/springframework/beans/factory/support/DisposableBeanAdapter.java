package com.yao.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.DisposableBean;
import com.yao.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月24日 10:30 上午
 */
public class DisposableBeanAdapter  implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //实现disposable接口
        if(bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }

        //通过xml方式
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
            Method method = beanName.getClass().getMethod(destroyMethodName);
            if (null == method) {
                throw new BeanException("Method is not find of" + destroyMethodName);
            }
            method.invoke(bean);
        }
    }
}
