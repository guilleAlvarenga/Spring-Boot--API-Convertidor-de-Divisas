package com.guilleS.response;

import lombok.Data;

@Data
public class ConversionResponse {
    private boolean success;
    private String from;
    private String to;
    private double amount;
    private String result;
}
