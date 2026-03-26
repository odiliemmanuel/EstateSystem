package com.realestate.controllers;


import com.realestate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realestate.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.realestate.exceptions.InvalidGatePassException;
import com.realestate.services.GatePassAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class ResidentControllers {

    @Autowired
    private GatePassAccessService gatePassAccessService;

    @RequestMapping("/generate-visitor-entry-code")
    public ResponseEntity<?> generateVisitorEntryCode(@RequestBody GenerateVisitorEntryCodeRequest generateVisitorEntryCodeRequest){
        try{
            gatePassAccessService.generateVisitorEntryCode(generateVisitorEntryCodeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(generateVisitorEntryCodeRequest);
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @RequestMapping("/generate-resident-entry-code")
    public ResponseEntity<?> generateResidentEntryCode(@RequestBody GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest){
        try{
            gatePassAccessService.generateResidentEntryCode(generateResidentEntryCodeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(generateResidentEntryCodeRequest);
        }
        catch(InvalidGatePassException error){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error.getMessage());
        }
    }



}

