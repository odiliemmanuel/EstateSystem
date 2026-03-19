package com.realestate.controllers;

import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.dtos.responses.OnboardResidentResponse;
import com.realestate.services.ResidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class EstateManagerControllers {

    @Autowired
    private ResidentManagementService  residentManagementService;



}
