package com.yao.springframework.context;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月27日 4:39 下午
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
