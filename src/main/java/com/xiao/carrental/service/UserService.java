package com.xiao.carrental.service;

import com.xiao.carrental.entity.User;

/**
 * @author kongx
 * @date 2022/7/4
 * @description
 */
public interface UserService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User login(String username, String password);
}
