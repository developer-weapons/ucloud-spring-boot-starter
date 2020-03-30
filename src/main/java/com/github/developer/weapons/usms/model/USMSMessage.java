package com.github.developer.weapons.usms.model;

import lombok.Data;

import java.util.List;

@Data
public class USMSMessage {
    private List<String> phoneNumbers;
    private String templateId;
}
