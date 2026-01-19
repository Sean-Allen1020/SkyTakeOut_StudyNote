package com.loginfileuploadcode.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

public class JwtUtil {
    /**
     * 生成Jwt令牌
     * @param claims
     * @param secretKey
     * @param milisTtl
     * @return
     */
    public static String createJwt(Map<String, Object> claims, String secretKey, Long milisTtl) {
        //1. 密钥
        SecretKey key = hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        //2. 生成令牌
        String jwt = Jwts.builder()
                    .signWith(key)    //算法，密钥
                    .claims(claims)    //自定义信息
                    .expiration(new Date(System.currentTimeMillis() + milisTtl)) //令牌生效时间
                    .compact();     //生成令牌
        return jwt;
        }

    /**
     * 解析Jwt令牌
     * @param jwt
     * @param secretKey
     * @return
     */
    public static Claims parseJwt(String jwt, String secretKey) {

        SecretKey key = hmacShaKeyFor(Decoders.BASE64.decode(secretKey));

        Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getPayload();
        return claims;
    }

}
