package com.github.developer.weapons.config;

import com.github.developer.weapons.service.UFileService;
import com.github.developer.weapons.service.USMSService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({UFileProperties.class, USMSProperties.class})
public class UCloudConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public USMSService usmsService() {
        return new USMSService();
    }

    @Bean
    @ConditionalOnMissingBean
    public UFileService uFileService() {
        return new UFileService();
    }
}
