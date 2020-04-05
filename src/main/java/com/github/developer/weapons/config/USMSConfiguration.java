package com.github.developer.weapons.config;

import com.github.developer.weapons.service.UFileService;
import com.github.developer.weapons.service.USMSService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(USMSService.class)
public class USMSConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public USMSService uSmsService() {
        return new USMSService();
    }

    @Bean
    @ConditionalOnMissingBean
    public UFileService uFileService() {
        return new UFileService();
    }
}
