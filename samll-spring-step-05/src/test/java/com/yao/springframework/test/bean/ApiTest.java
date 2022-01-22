package com.yao.springframework.test.bean;

import cn.hutool.json.XML;
import com.yao.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.yao.springframework.beans.factory.xml.XmlBeanDefinitionLoader;
import org.junit.Test;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 4:38 下午
 */
public class ApiTest {

    @Test
    public void test_factory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionLoader xmlBeanDefinitionLoader = new XmlBeanDefinitionLoader(defaultListableBeanFactory);
        xmlBeanDefinitionLoader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");

        String result = userService.queryUserName();
        System.out.println(result);
    }
}
