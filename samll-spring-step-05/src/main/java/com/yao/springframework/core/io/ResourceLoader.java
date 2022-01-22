package com.yao.springframework.core.io;

/**
 * @author yaowenlei
 * @description 资源加载器
 * @date 2022年01月20日 10:10 上午
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
