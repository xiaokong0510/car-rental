//package com.xiao.carrental.interceptor;
//
//import com.xiao.carrental.common.ReturnCode;
//import com.xiao.carrental.entity.User;
//import com.xiao.carrental.exception.BusinessException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.*;
//
///**
// * @author kongx
// * @date 2022/7/8
// * @description
// */
//@Slf4j
//@Component
//public class AuthenticationInterceptor implements HandlerInterceptor {
//
//    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
//            Arrays.asList("/user/login", "/login.html")));
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String path = request.getRequestURI().substring(request.getContextPath().length());
//        boolean allowedPath = ALLOWED_PATHS.contains(path);
//        if (allowedPath) {
//            return true;
//        }
//        User user = (User) request.getSession().getAttribute("user");
//        if (Objects.nonNull(user)) {
//            IdentityInfo identityInfo = new IdentityInfo();
//            identityInfo.setUserName(user.getUsername());
//            request.setAttribute("user", identityInfo);
//            return true;
//        } else {
//            throw new BusinessException(ReturnCode.NOT_LOGIN);
//        }
//    }
//}
