package com.yao.springframework.beans.factory;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月23日 3:54 下午
 */
public interface InitializingBean{
    void afterPropertiesSet() throws Exception;
}
