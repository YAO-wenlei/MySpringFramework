package com.yao.springframework.context;

import com.yao.springframework.context.ApplicationEvent;

import java.util.EventListener;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月27日 1:59 下午
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
