package com.loginfileuploadcode.configuration;

//import com.loginfileuploadcode.interceptor.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired  // 创建拦截器实例
//    private DemoInterceptor demoInterceptor;
//
//    @Override   // 拦截器添加方法
//    public void addInterceptors(InterceptorRegistry registry) {
//                            // 注册拦截器                      // 设置拦截路径， **为所有
//        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
//    }
//}
