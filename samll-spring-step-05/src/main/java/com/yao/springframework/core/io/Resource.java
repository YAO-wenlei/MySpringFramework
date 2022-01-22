package com.yao.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author tlbcc
 * @description 处理资源加载流
 * @date 2022/1/19 11:02 下午
**/

public interface Resource {
    InputStream getInputStream() throws IOException;
}
