package com.loginfileuploadcode.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 令牌生成相关属性，密钥和有效期
 */
@ConfigurationProperties(prefix = "test.jwt")
@Component
@Data
public class JwtProperties {

    private String secretKey;
    private Long ttl;
}
