package com.github.developer.weapons.service;

import com.github.developer.weapons.model.USMSMessage;
import com.github.developer.weapons.model.USMSResult;

public interface USMSService {
    USMSResult send(USMSMessage message);
}
