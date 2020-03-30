package com.github.developer.weapons.usms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * USMS 配置，参考文档 https://docs.ucloud.cn/usms/sdk_docs/7009
 */
@Data
@ConfigurationProperties(prefix = "spring.usms")
public class USMSProperties {
    @NotNull
    private String projectId;
    @NotNull
    private String publicKey;
    @NotNull
    private String privateKey;
    @NotNull
    private String signContent;
}
