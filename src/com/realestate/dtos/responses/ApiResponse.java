package com.realestate.dtos.responses;

import lombok.Data;

@Data
public class ApiResponse {

    private Object data;
    private boolean success;
    private String status;
    private Object message;

}
