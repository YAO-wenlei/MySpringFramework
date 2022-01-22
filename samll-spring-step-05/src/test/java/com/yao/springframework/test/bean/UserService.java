package com.yao.springframework.test.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author YAO_WENLEI
 * @description:
 * @since 2022-01-17 15:39:20
 */
@Getter
@Setter
public class UserService {
    private String name;
    private String userId;

    UserDao userDao = new UserDao();

    public UserService() {

    }

    public UserService(String name){
        this.name = name;
    }

    public void queryUserinfo() {
        System.out.println(name);
    }

    public String queryUserName() {
        return userDao.getUserName(userId);
    }
}
