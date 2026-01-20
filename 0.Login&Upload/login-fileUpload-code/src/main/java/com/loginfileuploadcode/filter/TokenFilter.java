package com.loginfileuploadcode.filter;

import com.loginfileuploadcode.properties.JwtProperties;
import com.loginfileuploadcode.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    private JwtProperties jwtProperties = new JwtProperties();

    //拦截请求方法
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取请求路径
        String uri = request.getRequestURI();

        // 2. 判断路径是否 包含 登录路径，如果有就直接放行
        if (uri.contains("/login") || uri.contains("/index.html") || uri.contains("/CSS/") || uri.contains("/json/")) {
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }
        // 3. 获取请求头中的token
        String token = request.getHeader("token");
        // 4. 并判断token是否存在或是空字符串
        if (token == null || token.isEmpty()) {
            // 设置响应码为401，以返回给前端            401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 5. 校验token           //因为Filter不能@AutoWired，所以手动设置密钥，常规情况下不需要
        jwtProperties.setSecretKey("0yk3l8mRUqo0b514atzOJ3a0OFDk2BNX2xBLxEE7xZQ=");
        try{
            JwtUtil.parseJwt(token, jwtProperties.getSecretKey());
        }catch (Exception e){
            log.info("令牌非法，响应401");
            // 设置响应码为401，以返回给前端            401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 6. 校验通过则放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);
    }
}
