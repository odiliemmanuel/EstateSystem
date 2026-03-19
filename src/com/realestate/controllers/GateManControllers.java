package com.realestate.controllers;

import com.realestate.dtos.requests.ValidateCodeRequest;
import com.realestate.dtos.responses.ValidateCodeResponse;
import com.realestate.exceptions.GatePassDoesNotExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.realestate.services.GatePassAccessService;

@Controller
public class GateManControllers {

    @Autowired
    private GatePassAccessService services;

    public ResponseEntity<?> validateGatePass(ValidateCodeRequest validateCodeRequest){
        try {
            ValidateCodeResponse validateCodeResponse = new ValidateCodeResponse();
            return ResponseEntity.status(HttpStatus.FOUND).body(validateCodeResponse);
        }
        catch(GatePassDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

}
