package com.loginfileuploadcode.interceptor;

import com.loginfileuploadcode.properties.JwtProperties;
import com.loginfileuploadcode.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
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

        // 判断当前拦截到的是 Controller方法，还是其它资源
        if(!(handler instanceof HandlerMethod)){
            // 如果拦截到的非Controller方法，则放行 (事实上就是放行静态资源)
            return true;
        }

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
        }
        catch (ExpiredJwtException e) {
            // token 过期
            log.info("令牌过期");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        catch (SignatureException e) {
            // 签名不对，可能被篡改，或者密钥不匹配
            log.info("签名不对，可能被篡改，或者密钥不匹配");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        catch (JwtException | IllegalArgumentException e) {
            // 其余 JWT 非法情况
            log.info("其余Jwt非法情况");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        
        // 6. 校验通过则放行
        log.info("令牌合法，放行");
        return true;
    }
}
