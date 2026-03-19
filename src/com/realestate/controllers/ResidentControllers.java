package com.realestate.controllers;

import com.realestate.data.models.Resident;
import com.realestate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.realestate.dtos.responses.OnboardResidentResponse;
import com.realestate.exceptions.ResidentDoesNotExistException;
import com.realestate.services.GatePassAccessService;
import com.realestate.services.ResidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ResidentControllers {


}
