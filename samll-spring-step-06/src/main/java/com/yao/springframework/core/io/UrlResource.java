package com.yao.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.sun.deploy.util.IcoEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author yaowenlei
 * @description
 * @date 2022年01月20日 9:50 上午
 */
public class UrlResource implements Resource{
    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url, "url not must to be null");
        this.url = url;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try {
            return urlConnection.getInputStream();
        } catch (IOException e) {
            if (urlConnection instanceof HttpURLConnection) {
                ((HttpURLConnection)urlConnection).disconnect();
            }
            throw e;
        }
    }
}
