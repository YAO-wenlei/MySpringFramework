package com.yao.springframework.context.event;

import com.yao.springframework.context.ApplicationContext;
import com.yao.springframework.context.ApplicationEvent;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月26日 2:22 下午
 */
public  class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
