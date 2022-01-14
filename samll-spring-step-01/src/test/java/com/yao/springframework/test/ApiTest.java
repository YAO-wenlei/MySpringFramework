package com.yao.springframework.test;

import com.yao.springframework.BeanDefinition;
import com.yao.springframework.BeanFactory;
import com.yao.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-14 14:52:17
 */
public class ApiTest {

    @Test
    public void testBeanDefinition() {
        //声明工厂
        BeanFactory beanFactory = new BeanFactory();
        //定义bean
        UserService userService = new UserService();

        BeanDefinition beanDefinition = new BeanDefinition(userService);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService1 = (UserService) beanFactory.getBean("userService");

        userService1.queryUserInfo();
    }
}
