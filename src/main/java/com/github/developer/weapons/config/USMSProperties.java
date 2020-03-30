package com.github.developer.weapons.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * USMS 配置，参考文档 https://docs.ucloud.cn/usms/sdk_docs/7009
 */
@Data
@ConfigurationProperties(prefix = "spring.usms")
public class USMSProperties {
    private String projectId;
    private String publicKey;
    private String privateKey;
    private String signContent;
}
