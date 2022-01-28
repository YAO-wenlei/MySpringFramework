package com.yao.springframework.context;

import com.yao.springframework.beans.factory.HierarchicalBeanFactory;
import com.yao.springframework.beans.factory.ListableBeanFactory;
import com.yao.springframework.context.event.ApplicationEventMulticaster;
import com.yao.springframework.core.io.ResourceLoader;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月21日 4:00 下午
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
