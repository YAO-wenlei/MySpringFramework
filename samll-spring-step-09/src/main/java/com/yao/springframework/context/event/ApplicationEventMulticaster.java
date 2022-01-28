package com.yao.springframework.context.event;


import com.yao.springframework.context.ApplicationEvent;
import com.yao.springframework.context.ApplicationListener;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月26日 2:33 下午
 */
public interface ApplicationEventMulticaster {
    void addApplicationLister(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
