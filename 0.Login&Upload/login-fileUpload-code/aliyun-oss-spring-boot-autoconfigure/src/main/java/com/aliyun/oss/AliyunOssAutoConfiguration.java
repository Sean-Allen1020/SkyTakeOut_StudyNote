package com.aliyun.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AliyunOSSOperator的自动配置类
 */
@EnableConfigurationProperties(AliyunOssProperties.class)
@AutoConfiguration
public class AliyunOssAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliyunOssOperator aliyunOssOperator(AliyunOssProperties aliyunOssProperties) {

        return new AliyunOssOperator(aliyunOssProperties);
    }
}
