package com.realEstate.controllers;

import com.realEstate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realEstate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.realEstate.exceptions.ResidentDoesNotExistException;
import com.realEstate.services.GatePassAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ResidentControllers {

    @Autowired
    private GatePassAccessService services;

    @PostMapping("/generateResidentEntryCode")
    public ResponseEntity<?> generateResidentEntryCode(GenerateResidentEntryCodeRequest getResidentEntryCodeRequest) {
        try{
            GenerateResidentEntryCodeResponse generateResidentEntryCodeResponse = new GenerateResidentEntryCodeResponse();
            return ResponseEntity.status(HttpStatus.CREATED).body(generateResidentEntryCodeResponse);
        }
        catch(ResidentDoesNotExistException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
