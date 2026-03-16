package com.realEstate.utils;

import com.realEstate.data.models.GatePass;
import com.realEstate.data.models.Resident;
import com.realEstate.data.models.Type;
import com.realEstate.data.repositeries.ResidentRepository;
import com.realEstate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realEstate.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.realEstate.dtos.requests.OnboardResidentRequest;
import com.realEstate.dtos.requests.ValidateCodeRequest;
import com.realEstate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.realEstate.dtos.responses.GenerateVisitorEntryCodeResponse;
import com.realEstate.dtos.responses.OnboardResidentResponse;
import com.realEstate.dtos.responses.ValidateCodeResponse;

import java.time.LocalDateTime;


public class Mapper {
    private static ResidentRepository residentRepository;


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
        onboardResidentResponse.setResidentEmail(resident.getEmail());
        onboardResidentResponse.setEnabled(resident.isEnabled());
        return onboardResidentResponse;
    }

    public static GatePass mapEntryCodeToGatePass(GenerateResidentEntryCodeRequest generateResidentEntryCodeRequest){
        GatePass gatePass = new GatePass();
        gatePass.setResidentId(generateResidentEntryCodeRequest.getResidentId());
        gatePass.setEndTime(LocalDateTime.from(generateResidentEntryCodeRequest.getValidTill()));
        return gatePass;
    }

    public static GenerateResidentEntryCodeResponse mapResidentEntryCodeToResponse(GatePass gatePass) {
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
        gatePass.setPassType(Type.valueOf(validateCodeRequest.getCodeType()));
        return gatePass;
    }


    public static ValidateCodeResponse mapGatePassToResponse(GatePass gatePass){
        ValidateCodeResponse validateCodeResponse = new ValidateCodeResponse();
        validateCodeResponse.setResidentName(residentRepository.findById(gatePass.getResidentId()).get().getName());
        validateCodeResponse.setVisitorName(gatePass.getVisitor().getName());
        validateCodeResponse.setCodeType(gatePass.getPassType().toString());
        validateCodeResponse.setValid(true);
        return validateCodeResponse;
    }

}
