package com.loginfileuploadcode.configuration;

import com.loginfileuploadcode.interceptor.DemoInterceptor;
import com.loginfileuploadcode.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override   // 拦截器添加方法
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(tokenInterceptor)   // 注册拦截器
                .addPathPatterns("/**")             // 设置拦截路径， **为所有
                .excludePathPatterns("/login"       // 放行的请求路径，以及静态资源
                                    ,"/index.html"
                                    ,"/CSS/**"
                                    ,"/json/**"
                                    ,"/favicon.ico");
    }
}
