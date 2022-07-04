package com.xiao.carrental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiao.carrental.entity.User;
import com.xiao.carrental.mapper.UserMapper;
import com.xiao.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kongx
 * @date 2022/7/4
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username).eq(User::getPassword, password);
        return userMapper.selectOne(queryWrapper);
    }
}
