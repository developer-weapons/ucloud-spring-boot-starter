package com.github.codedrinker.usms.model;

import lombok.Data;

import java.util.List;

@Data
public class USMSMessage {
    private List<String> phoneNumbers;
    private String templateId;
}
