package com.realestate.controllers;

import com.realestate.dtos.requests.ValidateCodeRequest;
import com.realestate.dtos.responses.ValidateCodeResponse;
import com.realestate.exceptions.GatePassDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.realestate.services.GatePassAccessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RequestMapping("/user")
@RestController
public class GateManControllers {


    @Autowired
    private GatePassAccessService services;


    @PostMapping("/validate-gatepass")
    public ResponseEntity<?> validateGatePass(@RequestBody ValidateCodeRequest validateCodeRequest){
        try {
            services.validateCode(validateCodeRequest);
            return ResponseEntity.status(HttpStatus.FOUND).body(validateCodeRequest);
        }
        catch(GatePassDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }

}
