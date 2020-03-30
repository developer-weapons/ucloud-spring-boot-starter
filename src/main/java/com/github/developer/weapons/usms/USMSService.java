package com.github.developer.weapons.usms;

import com.github.developer.weapons.usms.model.USMSMessage;
import com.github.developer.weapons.usms.model.USMSResult;

public interface USMSService {
    USMSResult send(USMSMessage message);
}
