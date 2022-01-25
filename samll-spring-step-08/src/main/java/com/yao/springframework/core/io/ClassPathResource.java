package com.yao.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.yao.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yaowenlei
 * @description 根路径获取文件
 * @date 2022年01月19日 11:07 下午
 */
public class ClassPathResource implements Resource {
    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notBlank(path, "Path not must to be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(path);
        if (null == resourceAsStream) throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        return resourceAsStream;
    }
}
