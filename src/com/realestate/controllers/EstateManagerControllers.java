package com.realestate.controllers;

import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.exceptions.ResidentDoesNotExistException;
import com.realestate.services.ResidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/user")
public class EstateManagerControllers {

    @Autowired
    private ResidentManagementService  residentManagementService;


    @PostMapping("/add-new-resident")
    public ResponseEntity<?> onboardNewResident(@RequestBody OnboardResidentRequest onboardResidentRequest) {

        try {
            residentManagementService.onboardResident(onboardResidentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(onboardResidentRequest);
        }
        catch (ResidentDoesNotExistException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }

    }


    @GetMapping("/find-resident/{email}")
    public ResponseEntity<?> findResident(@PathVariable("email") String email) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(residentManagementService.viewResidentUsingMail(email));
        }
        catch (ResidentDoesNotExistException error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }



}
