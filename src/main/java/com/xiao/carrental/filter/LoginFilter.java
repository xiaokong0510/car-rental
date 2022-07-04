package com.xiao.carrental.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author kongx
 * @date 2022/7/4
 * @description
 */
@Slf4j
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

    private static final String[] WHITE_URLS = {"/login.html", "/user/login"};
    private static final String[] FILE_SUFFIX = {"jpeg", "jpg", "png", "gif", "bmp", "webp", "css", "js", "woff", "woff2"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        if (Arrays.asList(WHITE_URLS).contains(servletPath) || endWith(servletPath)) {
            filterChain.doFilter(request, response);
        } else {
            Object user = request.getSession().getAttribute("user");
            if (Objects.isNull(user)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/login.html");
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean endWith(String path) {
        for (String fileSuffix : FILE_SUFFIX) {
            if (path.endsWith(fileSuffix)) {
                return true;
            }
        }
        return false;
    }
}
