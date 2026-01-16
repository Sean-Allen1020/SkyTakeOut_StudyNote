package com.loginfileuploadcode;
import com.loginfileuploadcode.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt(){

        //1. 自定义信息
        Map<String,Object> map = new HashMap<>();
        map.put("id", 1);
        //2. 密钥
        Key key = Jwts.SIG.HS256.key().build();

        //3. 生成令牌
        String jwt = Jwts.builder()
                    .signWith(key)     //密钥
                    .claims(map)    //自定义信息
                    .expiration(new Date(System.currentTimeMillis() + 3600000)) //令牌生效时间
                    .compact();     //生成令牌

        System.out.println(jwt);
    }
}
