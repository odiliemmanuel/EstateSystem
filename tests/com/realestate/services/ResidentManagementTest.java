package com.realestate.services;


import com.realestate.data.repositeries.GatePassRepository;
import com.realestate.data.repositeries.ResidentRepository;
import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.dtos.responses.OnboardResidentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ResidentManagementTest {

    @Autowired
    ResidentManagementService residentManagementService;
    OnboardResidentRequest onboardResidentRequest;
    OnboardResidentResponse onboardResidentResponse;

    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    GatePassRepository  gatePassRepository;




    @BeforeEach
    void setUp() {
        residentRepository.deleteAll();
        gatePassRepository.deleteAll();
        onboardResidentRequest = new OnboardResidentRequest();
        onboardResidentResponse = new OnboardResidentResponse();

        onboardResidentRequest.setName("Odili");
        onboardResidentRequest.setPhoneNumber("123456789");
        onboardResidentRequest.setEmail("email@gmail.com");
        onboardResidentRequest.setAddress("room 5");
        residentManagementService.onboardResident(onboardResidentRequest);
    }



    @Test
    public void testThatICanOnboardResident() {

        assertEquals("Odili", onboardResidentRequest.getName());
        assertEquals(1, residentRepository.count());

    }

    @Test
    public void testThatIDeleteResident_NumberOfResidentsDecreases(){
        onboardResidentRequest.setName("Resident");
        onboardResidentRequest.setPhoneNumber("123456789");
        onboardResidentRequest.setEmail("email");
        onboardResidentRequest.setAddress("room 1");
        residentManagementService.onboardResident(onboardResidentRequest);

        assertEquals(2, residentRepository.count());

        residentManagementService.deleteResident("email@gmail.com");
        assertEquals(1, residentRepository.count());
    }


    @Test
    public void testThatICanViewResident_UsingItsEmail(){
        onboardResidentRequest.setName("Resident");
        onboardResidentRequest.setPhoneNumber("123456789");
        onboardResidentRequest.setEmail("email");
        onboardResidentRequest.setAddress("room 1");
        residentManagementService.onboardResident(onboardResidentRequest);


        onboardResidentRequest.setName("Ejeh");
        onboardResidentRequest.setPhoneNumber("07046731194");
        onboardResidentRequest.setEmail("odili09@gmail.com");
        onboardResidentRequest.setAddress("room 1");
        residentManagementService.onboardResident(onboardResidentRequest);

        assertEquals("Ejeh", residentManagementService.viewResidentUsingMail("odili09@gmail.com").getName());

    }

    @Test
    public void testThatICanViewAllResidents() {
        onboardResidentRequest.setName("Ejeh");
        onboardResidentRequest.setPhoneNumber("07046731194");
        onboardResidentRequest.setEmail("odili09@gmail.com");
        onboardResidentRequest.setAddress("room 7");
        residentManagementService.onboardResident(onboardResidentRequest);

        onboardResidentRequest.setName("Resident");
        onboardResidentRequest.setPhoneNumber("123456789");
        onboardResidentRequest.setEmail("email");
        onboardResidentRequest.setAddress("room 1");
        residentManagementService.onboardResident(onboardResidentRequest);

        assertEquals(3, residentRepository.count());

    }

}