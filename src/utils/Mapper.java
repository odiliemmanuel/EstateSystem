package utils;

import data.models.GatePass;
import data.models.Resident;
import data.repositeries.ResidentRepository;
import dtos.requests.GenerateResidentEntryCodeRequest;
import dtos.requests.GenerateVisitorEntryCodeRequest;
import dtos.requests.OnboardResidentRequest;
import dtos.responses.GenerateResidentEntryCodeResponse;
import dtos.responses.GenerateVisitorEntryCodeResponse;
import dtos.responses.OnboardResidentResponse;
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

    public static GenerateVisitorEntryCodeResponse mapGatePassToResponse(GatePass gatePass){
        GenerateVisitorEntryCodeResponse generateVisitorEntryCodeResponse = new GenerateVisitorEntryCodeResponse();
        generateVisitorEntryCodeResponse.setCode(RandomCodeGenerator.generateCode());
        generateVisitorEntryCodeResponse.setVisitorName(gatePass.getVisitor().getName());
        generateVisitorEntryCodeResponse.setValidTill(String.valueOf(gatePass.getEndTime()));
        generateVisitorEntryCodeResponse.setCodeType(gatePass.getPassType().toString());
        return generateVisitorEntryCodeResponse;
    }

}
