package com.github.developer.weapons.model;

import lombok.Data;

@Data
public class USMSResult {
    private String sessionNo;
    protected Integer retCode;
    protected String action;
    protected String message;
}
