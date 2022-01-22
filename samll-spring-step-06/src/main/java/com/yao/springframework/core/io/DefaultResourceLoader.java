package com.yao.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.URL;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 10:28 上午
 */
public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "location must to be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                return new UrlResource(new URL(location));
            } catch (Exception e) {
                return new FileSystemResource(location);
            }
        }
    }
}
