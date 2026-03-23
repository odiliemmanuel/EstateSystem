package com.realestate.controllers;


import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.exceptions.ResidentDoesNotExistException;
import com.realestate.services.ResidentManagementService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class ResidentControllers {

//    private final ResidentManagementService residentManagementService;

//    public ResidentControllers(ResidentManagementService residentManagementService) {
//        this.residentManagementService = residentManagementService;
//    }


}

