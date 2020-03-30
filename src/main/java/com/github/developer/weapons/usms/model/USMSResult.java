package com.github.developer.weapons.usms.model;

import lombok.Data;

@Data
public class USMSResult {
    private String sessionNo;
    protected Integer retCode;
    protected String action;
    protected String message;
}
