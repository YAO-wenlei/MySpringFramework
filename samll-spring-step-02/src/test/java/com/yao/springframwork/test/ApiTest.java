package com.yao.springframwork.test;

import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yao.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-15 14:09:31
 */
public class ApiTest {

    @Test
    public void test_beanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
