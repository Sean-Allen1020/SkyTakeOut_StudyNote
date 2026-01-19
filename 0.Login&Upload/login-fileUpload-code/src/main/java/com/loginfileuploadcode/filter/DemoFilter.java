package com.loginfileuploadcode.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")  // * 为占位符，表示拦截所有请求
public class DemoFilter implements Filter {

    //初始化方法，web服务器启动的时候执行一次，不需要时可以不重写
    //不常用
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 过滤器初始化方法");
    }

    //拦截请求方法，拦截到请求之后执行，执行多次。是核心方法，必须重写实现
    //常用
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //拦截前端发送的请求
        log.info("doFilter 过滤器拦截了请求");
        //放行请求
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("doFilter 过滤器放行了请求");
    }

    //销毁方法，web服务器关闭的时候执行一次，用于资源释放，不需要时可以不重写
    //不常用
    public void destroy() {
        log.info("destroy 过滤器销毁方法");
    }
}
