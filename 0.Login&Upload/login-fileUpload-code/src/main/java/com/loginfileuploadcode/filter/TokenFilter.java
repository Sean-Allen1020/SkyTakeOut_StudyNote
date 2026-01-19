package com.loginfileuploadcode.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/login")
public class TokenFilter implements Filter {

    //初始化方法
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 过滤器初始化方法");
    }

    //拦截请求方法
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //拦截前端发送的请求
        log.info("doFilter 过滤器拦截了请求");
        log.info("拦截前逻辑...");
        //放行请求
        filterChain.doFilter(servletRequest, servletResponse);
        //放行后
        log.info("doFilter 过滤器放行了请求");
        log.info("拦截后逻辑...");
    }

    //销毁方法
    public void destroy() {
        log.info("destroy 过滤器销毁方法");
    }
}
