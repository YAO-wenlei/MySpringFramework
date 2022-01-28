
package com.yao.springframework.context.event;

import com.yao.springframework.beans.factory.BeanFactory;
import com.yao.springframework.beans.factory.FactoryBean;
import com.yao.springframework.context.ApplicationEvent;
import com.yao.springframework.context.ApplicationListener;

import java.util.List;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月27日 4:49 下午
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (ApplicationListener applicationListener : getApplicationListeners(event)) {
            applicationListener.onApplicationEvent(event);
        }
    }
}



