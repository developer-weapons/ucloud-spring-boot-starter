package com.github.developer.weapons.service;

import cn.ucloud.common.pojo.Account;
import cn.ucloud.usms.client.DefaultUSMSClient;
import cn.ucloud.usms.client.USMSClient;
import cn.ucloud.usms.model.SendUSMSMessageParam;
import cn.ucloud.usms.model.SendUSMSMessageResult;
import cn.ucloud.usms.pojo.USMSConfig;
import com.github.developer.weapons.config.USMSProperties;
import com.github.developer.weapons.model.USMSMessage;
import com.github.developer.weapons.model.USMSResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class USMSService implements InitializingBean {

    @Autowired
    private USMSProperties usmsProperties;

    private USMSClient client;

    public USMSResult send(USMSMessage message) {
        SendUSMSMessageParam param = new SendUSMSMessageParam(message.getPhoneNumbers(), message.getTemplateId());
        param.setTemplateParams(message.getTemplateParams());

        if (usmsProperties.getSignContent() == null) {
            throw new RuntimeException("ucloud.usms.signContent is missing");
        }
        param.setSigContent(usmsProperties.getSignContent());
        param.setProjectId(usmsProperties.getProjectId());
        try {
            SendUSMSMessageResult result = client.sendUSMSMessage(param);
            if (result != null) {
                USMSResult usmsResult = new USMSResult();
                usmsResult.setAction(result.getAction());
                usmsResult.setMessage(result.getMessage());
                usmsResult.setRetCode(result.getRetCode());
                usmsResult.setSessionNo(result.getSessionNo());
                return usmsResult;
            }
        } catch (Exception e) {
            log.error("USMS_MESSAGE_SEND_ERROR, message : {}", message, e);
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        if (usmsProperties.getPrivateKey() == null) {
            throw new RuntimeException("ucloud.usms.privateKey is missing");
        }
        if (usmsProperties.getPublicKey() == null) {
            throw new RuntimeException("ucloud.usms.publicKey is missing");
        }
        client = new DefaultUSMSClient(new USMSConfig(
                new Account(usmsProperties.getPrivateKey(), usmsProperties.getPublicKey())));
    }
}
