package com.yao.springframwork.test.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-15 18:09:58
 */
@Getter
@Setter
public class UserService {
    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println(name);
    }
}
