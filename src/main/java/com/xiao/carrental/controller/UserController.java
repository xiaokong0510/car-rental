package com.xiao.carrental.controller;

import com.xiao.carrental.dto.response.CommonResult;
import com.xiao.carrental.entity.User;
import com.xiao.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author kongx
 * @date 2022/7/4
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public CommonResult<User> login(@RequestBody User user, HttpServletRequest request) {
        User res = userService.login(user.getUsername(), user.getPassword());
        if (Objects.nonNull(res)) {
            request.getSession().setAttribute("user", res);
        }
        return CommonResult.success(res);
    }

    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/login.html");
    }
}
