package com.realEstate.dtos.responses;

import lombok.Data;

@Data
public class ValidateCodeResponse {

    private String residentName;
    private String visitorName;
    private String codeType;
    private boolean isValid;
}
