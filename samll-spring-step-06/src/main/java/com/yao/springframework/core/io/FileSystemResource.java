package com.yao.springframework.core.io;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yaowenlei
 * @description 系统文件获取文件
 * @date 2022年01月19日 11:42 下午
 */
public class FileSystemResource implements Resource{
    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }
    @Override
    public InputStream getInputStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.file);
        return fileInputStream;
    }

    public final String getPath() {
        return this.path;
    }
}
