package com.loginfileuploadcode;
import com.loginfileuploadcode.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

public class JwtTest {

    /**
     * 生成Jwt令牌
     */
    @Test
    public void testGenerateJwt(){

        //1. 自定义信息
        Map<String,Object> map = new HashMap<>();
        map.put("id", 1);
        //2. 签名密钥
        // 字符串形式的Base64编码密钥需要解码后通过hmacShaKeyFor()方法转换成Key类才能存入signWith里
        SecretKey key = hmacShaKeyFor(Decoders.BASE64.decode("+bQk2X5Wn9J1IbWDPPXEUnY9wYSdNWoDOPgTH3fpgEY="));

        //3. 生成令牌
        String jwt = Jwts.builder()
                    .signWith(key)     //签名密钥
                    .claims(map)    //自定义信息
                    .expiration(new Date(System.currentTimeMillis() + 3600000)) //令牌生效时间
                    .compact();     //生成令牌

        System.out.println(jwt);
    }

    /**
     * 解析Jwt令牌
     */
    @Test
    public void testParseJwt(){
        String key = "+bQk2X5Wn9J1IbWDPPXEUnY9wYSdNWoDOPgTH3fpgEY=";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzY4NTc1NDI0fQ.AVaSAaRNkK9XTNbjLhJfmomiWI0G68vIQ4p0nWoEfpI";

        Claims claims = Jwts.parser()
                        .verifyWith(hmacShaKeyFor(Decoders.BASE64.decode(key)))     //验证签名密钥
                        .build()
                        .parseSignedClaims(token)   //解析令牌
                        .getPayload();

        System.out.println(claims);
    }
}
