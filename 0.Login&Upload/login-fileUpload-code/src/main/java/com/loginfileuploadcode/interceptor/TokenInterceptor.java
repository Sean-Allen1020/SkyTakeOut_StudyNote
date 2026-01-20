package com.loginfileuploadcode.interceptor;

import com.loginfileuploadcode.properties.JwtProperties;
import com.loginfileuploadcode.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1. 获取请求路径
        // 2. 判断路径是否 包含 登录路径，如果有就直接放行
        //以上两个步骤在configuration包中的 WebConfig类中实现

        // 3. 获取请求头中的token
        String token = request.getHeader("token");
        // 4. 并判断token是否存在或是空字符串
        if (token == null || token.isEmpty()) {
            // 设置响应码为401，以返回给前端            401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 5. 校验token
        try{
            JwtUtil.parseJwt(token, jwtProperties.getSecretKey());
        }catch (Exception e){
            log.info("令牌非法，响应401");
            // 设置响应码为401，以返回给前端            401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 6. 校验通过则放行
        log.info("令牌合法，放行");
        return true;
    }
}
