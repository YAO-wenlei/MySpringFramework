package com.yao.springframework.context.support;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import com.yao.springframework.beans.BeanException;
import com.yao.springframework.beans.factory.BeanNameAware;
import com.yao.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.yao.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.yao.springframework.beans.factory.config.BeanPostProcessor;
import com.yao.springframework.context.ApplicationEvent;
import com.yao.springframework.context.ApplicationListener;
import com.yao.springframework.context.ConfigurableApplicationContext;
import com.yao.springframework.context.event.ApplicationEventMulticaster;
import com.yao.springframework.context.event.ContextCloseEvent;
import com.yao.springframework.context.event.SimpleApplicationEventMulticaster;
import com.yao.springframework.core.io.DefaultResourceLoader;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月21日 4:16 下午
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    /**
     * @return void
     * @author yaowenlei
     * @description 刷新容器
     * @date 2022/1/21 4:18 下午
     **/
    @Override
    public void refresh() throws BeanException {

        //1,创建Factory，并加载beanDefinition
        refreshBeanFactory();

        //2,获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3,添加ApplicationContextAwareProcessor 让实现applicationAware接口的bean有感知容器的能力
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4,bean实例化之前，执行BeanFactoryPostProcessor  可以获取beanDefinition信息 并进行修改
        invokeBeanFactoryProcessor(beanFactory);

        //5,在bean实例化之前 注册postProcessor   此时的BeanPostProcessor的实现类已经是创建好的
        registryBeanPostProcessor(beanFactory);


        //6,初始化事件发布
        initApplicationEventMulticaster();

        //7,注册事件监听器
        registerListeners();

        //8,实例化单例的bean对象
        beanFactory.proInstantiateSingletons();

        //9,发布容器刷新完成事件
        finishRefresh();

    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    private void invokeBeanFactoryProcessor(ConfigurableListableBeanFactory beanFactory) {
        //获取到所有实现BeanFactoryPostProcessor接口的bean
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    private void registryBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingletonBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,simpleApplicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeanOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationLister(applicationListener);
        }

    }

    private void finishRefresh() {
        publishEvent(new ContextCloseEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeanOfType(type);
    }

    @Override
    public Object getBean(String beanName) throws BeanException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeanException {
        return getBeanFactory().getBean(beanName,args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(beanName,requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextCloseEvent(this));
        getBeanFactory().destroySingletons();
    }
}
