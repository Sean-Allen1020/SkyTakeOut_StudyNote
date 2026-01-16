//package com.loginfileuploadcode;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Encoders;
//import org.junit.jupiter.api.Test;
//
//import javax.crypto.SecretKey;
//
//public class KeyGenerator {
//
//    @Test
//    public void keyGen(){
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        System.out.println(Encoders.BASE64.encode(key.getEncoded()));
//    }
//}
