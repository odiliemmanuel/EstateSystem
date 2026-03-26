package com.realestate.dtos.requests;

import lombok.Data;

@Data
public class InviteVisitorRequest {

    private String visitorName;
    private String visitorPhone;
    private String purposeOfVisit;
}
