package com.loginfileuploadcode.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component  //实现拦截器的前提是需要注册一个配置类(configuration包中)
public class DemoInterceptor implements HandlerInterceptor {
    @Override //目标资源方法访问前执行，返回true则放行，false则不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        return true;
    }

    @Override //目标资源方法(Object handler)访问完成后，执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
    }

    @Override //视图渲染完毕后执行，前后端分离的情况不需要用此方法
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("afterCompletion...");
    }
}
