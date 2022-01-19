package com.yao.springframwork.test;

import com.yao.springframework.beans.PropertyValue;
import com.yao.springframework.beans.PropertyValues;
import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.config.BeanReference;
import com.yao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yao.springframwork.test.bean.UserDao;
import com.yao.springframwork.test.bean.UserService;
import org.junit.Test;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 15:39:30
 */
public class ApiTest {
    @Test
    public void test_beanFactory(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue userId = new PropertyValue("userId", "10001");
        PropertyValue propertyValue = new PropertyValue("userDao", new BeanReference("userDao"));
        propertyValues.addPropertyValue(userId);
        propertyValues.addPropertyValue(propertyValue);

        defaultListableBeanFactory.registerBeanDefinition("userService",new BeanDefinition(UserService.class,propertyValues));
        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        String s = userService.queryUserName();
        System.out.println(s);
    }
}
