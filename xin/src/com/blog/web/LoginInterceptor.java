package com.blog.web;

import com.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xin on 17-5-23.
 */

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    // 在执行controller前执行
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return true;
        }
        String uri = request.getRequestURI();
        String loginUrl = "login.html";
        String requestUrl = uri.substring(uri.lastIndexOf('/'), uri.length());
        if (requestUrl.equals(loginUrl)) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/pages/index.html");
        return true;
    }

    // 执行controller后渲染页面前执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {

    }

    // 渲染页面后执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {

    }
}
