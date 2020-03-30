package com.github.developer.weapons.model;

import lombok.Data;

import java.util.List;

@Data
public class USMSMessage {
    private List<String> phoneNumbers;
    private String templateId;
    private List<String> templateParams;
}
