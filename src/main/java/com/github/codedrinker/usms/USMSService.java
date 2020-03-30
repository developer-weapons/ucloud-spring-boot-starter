package com.github.codedrinker.usms;

import com.github.codedrinker.usms.model.USMSMessage;
import com.github.codedrinker.usms.model.USMSResult;

public interface USMSService {
    USMSResult send(USMSMessage message);
}
