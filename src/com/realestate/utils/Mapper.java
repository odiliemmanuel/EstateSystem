package com.realestate.utils;

import com.realestate.data.models.GatePass;
import com.realestate.data.models.Resident;
import com.realestate.data.models.Type;
import com.realestate.data.repositeries.ResidentRepository;
import com.realestate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realestate.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.dtos.requests.ValidateCodeRequest;
import com.realestate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.realestate.dtos.responses.GenerateVisitorEntryCodeResponse;
import com.realestate.dtos.responses.OnboardResidentResponse;
import com.realestate.dtos.responses.ValidateCodeResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Component
public class Mapper {



    public static Resident mapToResident(OnboardResidentRequest residentRequest){
        Resident resident = new Resident();
        resident.setName(residentRequest.getName());
        resident.setEmail(residentRequest.getEmail());
        resident.setPhoneNumber(residentRequest.getPhoneNumber());
        resident.setHouseAddress(residentRequest.getAddress());
        return resident;
    }

    public static OnboardResidentResponse mapResidentToResponse(Resident resident){
        OnboardResidentResponse onboardResidentResponse = new OnboardResidentResponse();
        onboardResidentResponse.setResidentName(resident.getName());
        onboardResidentResponse.setDateRegistered(String.valueOf(resident.getDateRegistered()));
        onboardResidentResponse.setResidentId(resident.getId());
        return onboardResidentResponse;
    }

    public static GatePass mapEntryCodeToGatePass(GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest){
        GatePass gatePass = new GatePass();
        gatePass.setResidentId(generateResidentEntryCodeRequest.getResidentId());
        gatePass.setEndTime(generateResidentEntryCodeRequest.getValidTill());
        return gatePass;
    }

    public static GenerateResidentEntryCodeResponse mapResidentEntryCodeToResponse(GatePass gatePass, ResidentRepository residentRepository) {
        GenerateResidentEntryCodeResponse generateResidentEntryCodeResponse = new GenerateResidentEntryCodeResponse();
        generateResidentEntryCodeResponse.setCode(gatePass.getCode());
        generateResidentEntryCodeResponse.setCodeType(String.valueOf(gatePass.getPassType()));
        generateResidentEntryCodeResponse.setResidentName(residentRepository.findById(gatePass.getResidentId()).get().getName());
        generateResidentEntryCodeResponse.setValidTill(String.valueOf(gatePass.getEndTime()));
        generateResidentEntryCodeResponse.setDestination(residentRepository.findById(gatePass.getResidentId()).get().getHouseAddress());
        return generateResidentEntryCodeResponse;

    }

    public static GatePass mapVisitor(GenerateVisitorEntryCodeRequest visitorEntryCode){
        GatePass gatepass = new GatePass();
        gatepass.setResidentId(visitorEntryCode.getResidentId());
        gatepass.getVisitor().setPhoneNumber(visitorEntryCode.getVisitorPhone());
        gatepass.getVisitor().setPurposeOfVisit(visitorEntryCode.getPurposeOfVisit());
        gatepass.getVisitor().setName(visitorEntryCode.getVisitorName());
        return gatepass;
    }

    public static GenerateVisitorEntryCodeResponse mapVisitorToResponse(GatePass gatePass){
        GenerateVisitorEntryCodeResponse generateVisitorEntryCodeResponse = new GenerateVisitorEntryCodeResponse();
        generateVisitorEntryCodeResponse.setCode(RandomCodeGenerator.generateCode());
        generateVisitorEntryCodeResponse.setVisitorName(gatePass.getVisitor().getName());
        generateVisitorEntryCodeResponse.setValidTill(String.valueOf(gatePass.getEndTime()));
        generateVisitorEntryCodeResponse.setCodeType(gatePass.getPassType().toString());
        return generateVisitorEntryCodeResponse;
    }



    public static GatePass mapValidateCodeToGatePass(ValidateCodeRequest validateCodeRequest){
        GatePass gatePass = new GatePass();
        gatePass.setCode(validateCodeRequest.getCode());
        gatePass.setPassType(Type.valueOf(validateCodeRequest.getCodeType().toUpperCase()));
        return gatePass;
    }


    public static ValidateCodeResponse mapGatePassToResponse(GatePass gatePass, ResidentRepository residentRepository){
        ValidateCodeResponse validateCodeResponse = new ValidateCodeResponse();
        validateCodeResponse.setResidentName(residentRepository.findById(gatePass.getResidentId()).get().getName());
        validateCodeResponse.setVisitorName(gatePass.getVisitor().getName());
        validateCodeResponse.setCodeType(gatePass.getPassType().toString());
        validateCodeResponse.setValid(true);
        return validateCodeResponse;
    }

}
