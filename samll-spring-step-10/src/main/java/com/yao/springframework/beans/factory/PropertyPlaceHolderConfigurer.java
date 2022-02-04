package com.yao.springframework.beans.factory;

import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValue;
import com.yao.springframework.beans.PropertyValues;
import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.yao.springframework.core.io.DefaultResourceLoader;
import com.yao.springframework.core.io.Resource;
import com.yao.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author yaowenlei
 * @description  继承
 * @see BeanFactoryPostProcessor 可以实现修改BeanDefinition信息 在读取配置文件时 读入通过注解注入的属性
 * @date 2022年02月02日 8:14 下午
 */
public class PropertyPlaceHolderConfigurer implements BeanFactoryPostProcessor {

    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        try {
            //加载属性配置文件
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            //占位符替换属性值
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            //xml占位符赋值
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();

                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    value = resolvePlaceholder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(),value));
                }
            }
            //向容器中添加字符串解析器
            StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(valueResolver);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String resolvePlaceholder(String value, Properties properties) {
        String strVal =  value;
        StringBuffer buffer = new StringBuffer(strVal);

        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);

        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();

    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {
        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceHolderConfigurer.this.resolvePlaceholder(strVal, properties);
        }
    }

}
