package com.realEstate.controllers;

import com.realEstate.dtos.requests.ValidateCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.realEstate.services.GatePassAccessService;

@Controller
public class GateManControllers {

//    @Autowired
//    private GatePassAccessService services;

    public ResponseEntity<?> validateGatePass(ValidateCodeRequest validateCodeRequest){
        return null;
    }

}
