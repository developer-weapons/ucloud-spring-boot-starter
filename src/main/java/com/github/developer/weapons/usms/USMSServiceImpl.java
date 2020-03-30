package com.github.developer.weapons.usms;

import cn.ucloud.common.pojo.Account;
import cn.ucloud.usms.client.DefaultUSMSClient;
import cn.ucloud.usms.client.USMSClient;
import cn.ucloud.usms.model.SendUSMSMessageParam;
import cn.ucloud.usms.model.SendUSMSMessageResult;
import cn.ucloud.usms.pojo.USMSConfig;
import com.github.developer.weapons.usms.model.USMSMessage;
import com.github.developer.weapons.usms.model.USMSResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class USMSServiceImpl implements USMSService {

    @Autowired
    private USMSProperties usmsProperties;

    private USMSClient client = new DefaultUSMSClient(new USMSConfig(
            new Account(usmsProperties.getPrivateKey(), usmsProperties.getPublicKey())));


    @Override
    public USMSResult send(USMSMessage message) {
        SendUSMSMessageParam param = new SendUSMSMessageParam(message.getPhoneNumbers(), message.getTemplateId());
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
}
