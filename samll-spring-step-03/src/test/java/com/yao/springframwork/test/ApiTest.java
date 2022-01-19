package com.yao.springframwork.test;

import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yao.springframwork.test.bean.UserService;
import org.junit.Test;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-15 18:13:03
 */
public class ApiTest {

    @Test
    public void test_beanFactory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        defaultListableBeanFactory.registerBeanDefinition("UserService", beanDefinition);
        UserService bean = (UserService) defaultListableBeanFactory.getBean("UserService", "zhangsan");
        bean.queryUserInfo();
    }
}
