package com.github.codedrinker.usms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(USMSService.class)
@EnableConfigurationProperties(USMSProperties.class)
public class USMSConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public USMSService usmsService() {
        return new USMSServiceImpl();
    }
}