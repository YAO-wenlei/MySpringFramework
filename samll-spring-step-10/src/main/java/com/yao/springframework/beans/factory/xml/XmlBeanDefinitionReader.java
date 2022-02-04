package com.yao.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.PropertyValue;
import com.yao.springframework.beans.factory.config.BeanDefinition;
import com.yao.springframework.beans.factory.config.BeanReference;
import com.yao.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.yao.springframework.beans.factory.support.BeanDefinitionRegister;
import com.yao.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.yao.springframework.core.io.Resource;
import com.yao.springframework.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 1:46 下午
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegister beanDefinitionRegister) {
        super(beanDefinitionRegister);
    }

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader, BeanDefinitionRegister beanDefinitionRegister) {
        super(resourceLoader, beanDefinitionRegister);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try (InputStream inputStream = resource.getInputStream()) {
            doLoadBeanBeanDefinition(inputStream);
        } catch (IOException | ClassNotFoundException | DocumentException exception) {
            throw new BeanException("bean parse error for XML" + resource, exception);
        }
    }


    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeanException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for (String location : locations) {
            this.loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanBeanDefinition(InputStream inputStream) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        //通过注解扫描
        Element element = root.element("component-scan");
        if (null != element) {
            String scanPath = element.attributeValue("base-package");
            if (StrUtil.isEmpty(scanPath)) {
                throw new BeanException("The value of base-package attribute can not be empty or null");
            }
            scanPacks(scanPath);
        }

        List<Element> beanList = root.elements("bean");
        for (Element bean : beanList) {

            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");
            String scope = bean.attributeValue("scope");

            // 获取 Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);
            if (StrUtil.isNotEmpty(scope)) {
                beanDefinition.setScope(scope);
            }
            // 读取属性并填充
            List<Element> propertyList = bean.elements("property");
            // 读取属性并填充
            for (Element property : propertyList) {
                // 解析标签：property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if (getRegister().containsBeanDefinition(beanName)) {
                throw new BeanException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegister().registerBeanDefinition(beanName, beanDefinition);

        }
    }

    private void scanPacks(String scanPath) {
        String[] packs = StrUtil.splitToArray(scanPath,',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegister());
        scanner.doScan(packs);

    }
}
