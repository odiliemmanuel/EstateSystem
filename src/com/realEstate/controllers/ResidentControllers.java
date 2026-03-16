package com.realEstate.controllers;

import com.realEstate.dtos.requests.GenerateResidentEntryCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.realEstate.services.GatePassAccessService;

@Controller
public class ResidentControllers {

//    @Autowired
//    private GatePassAccessService services;

    public ResponseEntity<?> generateResidentEntryCode(GenerateResidentEntryCodeRequest getResidentEntryCodeRequest) {
        return  null;
    }
}
