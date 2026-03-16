package com.realEstate.services;

import com.realEstate.data.models.Resident;
import com.realEstate.dtos.requests.OnboardResidentRequest;
import com.realEstate.dtos.responses.OnboardResidentResponse;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ResidentManagementServiceTest {


    @Test
    public void tesThatIRegisterResident_NumberOfResidentsIncreases(){
        ResidentManagementService services = new ResidentManagementService();
        Resident resident = new Resident();
        OnboardResidentRequest request = new OnboardResidentRequest();
        OnboardResidentResponse response = new OnboardResidentResponse();
        resident.setPhoneNumber("123456789");
        resident.setName("Resident");
        resident.setHouseAddress("room 1");
        resident.setEnabled(true);
        resident.setEmail("email");
        resident.setDateRegistered(LocalDateTime.now());

        request.setName(resident.getName());
        request.setPhoneNumber(resident.getPhoneNumber());
        request.setEmail(resident.getEmail());
        request.setAddress(resident.getHouseAddress());
        assertEquals(0, services.viewResidents().size());

       services.onboardResident(request);
       assertEquals(0, services.viewResidents().size());




    }
}
