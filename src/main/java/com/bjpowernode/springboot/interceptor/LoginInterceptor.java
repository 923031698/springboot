package com.bjpowernode.springboot.interceptor;

import com.bjpowernode.springboot.constants.Constant;
import com.bjpowernode.springboot.model.Users;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进入controller之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Users userInfo = (Users)request.getSession().getAttribute(Constant.LOGIN_USER);

        if (userInfo == null) {
            //未登录
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }
        //登录，当返回true的时候，就会继续去执行我们的controller
        return true;
    }
}