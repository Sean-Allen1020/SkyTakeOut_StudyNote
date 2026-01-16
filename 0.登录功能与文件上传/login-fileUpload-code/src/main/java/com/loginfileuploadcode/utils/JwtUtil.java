package com.loginfileuploadcode.utils;

import com.loginfileuploadcode.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    @Autowired
    private JwtProperties jwtProperties;

    public static String createJwt(String username, String password) {
        //1. 自定义信息
        Map<String,Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        //2. 密钥
//        Key key = Decoders.BASE64.decode(secretKey);
        //3. 生成令牌
        String jwt = Jwts.builder()
                    .signWith()    //算法，密钥
                    .claims(map)    //自定义信息
                    .expiration(new Date(System.currentTimeMillis() + 3600000)) //令牌生效时间
                    .compact();     //生成令牌
        }
}
