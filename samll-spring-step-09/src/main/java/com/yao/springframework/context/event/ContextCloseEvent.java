package com.yao.springframework.context.event;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月26日 2:25 下午
 */
public class ContextCloseEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextCloseEvent(Object source) {
        super(source);
    }
}
