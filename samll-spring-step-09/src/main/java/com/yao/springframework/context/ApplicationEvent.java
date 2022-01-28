package com.yao.springframework.context;

import java.util.EventObject;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月26日 2:08 下午
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
