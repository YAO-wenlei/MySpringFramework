package com.yao.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> userMap = new HashMap<>();

    static {
        userMap.put("10001", "张三");
        userMap.put("10002", "李四");
        userMap.put("10003", "王五");
    }

    public String getUserName(String userId) {
        return userMap.get(userId);
    }
}
