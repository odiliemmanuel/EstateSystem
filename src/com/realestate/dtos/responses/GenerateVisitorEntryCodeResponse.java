package com.realestate.dtos.responses;

import lombok.Data;

import java.time.LocalTime;

@Data
public class GenerateVisitorEntryCodeResponse {

    private String code;
    private String visitorName;
    private String validTill;
    private String codeType;
}
