package com.realestate.services;


import com.realestate.data.repositeries.GatePassRepository;
import com.realestate.data.repositeries.ResidentRepository;
import com.realestate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.dtos.requests.ValidateCodeRequest;
import com.realestate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.realestate.dtos.responses.OnboardResidentResponse;
import com.realestate.dtos.responses.ValidateCodeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Autowired
    GatePassAccessService gatePassAccessService;




    @BeforeEach
    void setUp() {
        residentRepository.deleteAll();
        onboardResidentRequest = new OnboardResidentRequest();
        onboardResidentResponse = new OnboardResidentResponse();
    }



    @Test
    public void testThatICanOnboardResident() {

        onboardResidentRequest.setName("Resident");
        onboardResidentRequest.setPhoneNumber("123456789");
        onboardResidentRequest.setEmail("email");
        onboardResidentRequest.setAddress("room 2");
        assertEquals("Resident", residentManagementService.onboardResident(onboardResidentRequest).getResidentName());
        assertEquals(1, residentRepository.count());

    }

    @Test
    public void testThatIDeleteResident_NumberOfResidentsDecreases(){
        onboardResidentRequest.setName("Resident");
        onboardResidentRequest.setPhoneNumber("123456789");
        onboardResidentRequest.setEmail("email");
        onboardResidentRequest.setAddress("room 1");
        residentManagementService.onboardResident(onboardResidentRequest);

        onboardResidentRequest.setName("Resident2");
        onboardResidentRequest.setPhoneNumber("123456784");
        onboardResidentRequest.setEmail("emails");
        onboardResidentRequest.setAddress("room 18");
        residentManagementService.onboardResident(onboardResidentRequest);

        assertEquals(2, residentRepository.count());
//
        residentManagementService.deleteResident("email");
        assertEquals(1, residentRepository.count());
    }


    @Test
    public void testThatIGenerateCodeForResident_codeIsValidAtGateTest(){
        onboardResidentRequest.setName("name");
        onboardResidentRequest.setPhoneNumber("123456789");
        onboardResidentRequest.setEmail("email@gmail.com");
        onboardResidentRequest.setAddress("room 5");
        OnboardResidentResponse onboardResidentResponse = residentManagementService.onboardResident(onboardResidentRequest);

        GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest = new GenerateResidentEntryCodeRequest();
        generateResidentEntryCodeRequest.setResidentId(onboardResidentResponse.getResidentId());
        generateResidentEntryCodeRequest.setValidTill(LocalDateTime.now().plusHours(4));
        GenerateResidentEntryCodeResponse residentEntryCodeResponse = gatePassAccessService.generateResidentEntryCode(generateResidentEntryCodeRequest);

        ValidateCodeRequest codeRequest = new ValidateCodeRequest();
        codeRequest.setCode(residentEntryCodeResponse.getCode());
        codeRequest.setCodeType("entry");
        ValidateCodeResponse validateCodeResponse = gatePassAccessService.validateCode(codeRequest);

        assertTrue(validateCodeResponse.isValid());
    }

}
