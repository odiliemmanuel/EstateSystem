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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class GatePassAccessServiceTest {

    @Autowired
    ResidentManagementService residentManagementService;
    OnboardResidentRequest onboardResidentRequest;
    OnboardResidentResponse onboardResidentResponse;

    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    GatePassRepository gatePassRepository;

    @Autowired
    GatePassAccessService gatePassAccessService;

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
    public void testThatIGenerateCodeForResident_codeIsValidAtGateTest(){

        OnboardResidentResponse onboardResidentResponse = residentManagementService.onboardResident(onboardResidentRequest);

        GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest = new GenerateResidentEntryCodeRequest();
        generateResidentEntryCodeRequest.setResidentId(onboardResidentResponse.getResidentId());
        generateResidentEntryCodeRequest.setValidTill(LocalDateTime.now().plusHours(4));
        GenerateResidentEntryCodeResponse residentEntryCodeResponse = gatePassAccessService.generateResidentEntryCode(generateResidentEntryCodeRequest);


        System.out.println("Generated code" +  residentEntryCodeResponse.getCode());

        ValidateCodeRequest codeRequest = new ValidateCodeRequest();
        codeRequest.setCode(residentEntryCodeResponse.getCode());
        codeRequest.setCodeType("entry");
        ValidateCodeResponse validateCodeResponse = gatePassAccessService.validateCode(codeRequest);

        System.out.println(codeRequest.getCode());
        System.out.println(validateCodeResponse.getCode());

        assertTrue(validateCodeResponse.isValid());
        assertEquals(onboardResidentRequest.getName(), validateCodeResponse.getCreatedBy());
        assertEquals(onboardResidentRequest.getName(), validateCodeResponse.getVisitorName());
        assertEquals("ENTRY", validateCodeResponse.getCodeType());
        assertEquals(onboardResidentRequest.getName(), validateCodeResponse.getResidentName());
    }
}
