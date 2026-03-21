package com.realestate.services;

import com.realestate.data.models.GatePass;
import com.realestate.data.models.Resident;
import com.realestate.data.models.Type;
import com.realestate.data.repositeries.GatePassRepository;
import com.realestate.data.repositeries.ResidentRepository;
import com.realestate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realestate.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.realestate.dtos.requests.ValidateCodeRequest;
import com.realestate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.realestate.dtos.responses.GenerateVisitorEntryCodeResponse;
import com.realestate.dtos.responses.ValidateCodeResponse;
import com.realestate.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.realestate.utils.Mapper;
import com.realestate.utils.RandomCodeGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Service
public class GatePassAccessService {


    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private GatePassRepository gatePassRepository;


    public String disableCode(String phoneNumber, String emailAddress) {
        Resident existingEmail = residentRepository.findByEmail(emailAddress);
        Resident existingNumber = residentRepository.findByPhoneNumber(phoneNumber);
        if (existingEmail != null && existingNumber != null) {
            throw new ResidentDoesNotExistException("Cannot find resident");
        }

        else{
            residentRepository.findByPhoneNumber(phoneNumber).setEnabled(false);
            return "Resident has been disabled";
        }

    }


    public String generateExitCode(String phoneNumber, String emailAddress){
        Resident existingEmail = residentRepository.findByEmail(emailAddress);
        Resident existingNumber = residentRepository.findByPhoneNumber(phoneNumber);
        if (existingEmail != null && existingNumber != null) {
            throw new ResidentDoesNotExistException("Resident does not exist");
        }

        else{
            RandomCodeGenerator.generateCode();
            return "Pass Code successfully generated";
        }


    }



    public GenerateVisitorEntryCodeResponse generateVisitorEntryCode(GenerateVisitorEntryCodeRequest visitorEntryCode){

        GatePass gatepass = Mapper.mapVisitor(visitorEntryCode);
        gatePassRepository.save(gatepass);
        return Mapper.mapVisitorToResponse(gatepass);

    }


    public GenerateResidentEntryCodeResponse generateResidentEntryCode(GenerateResidentEntryCodeRequest generateResidentEntryCode) {

        GatePass gatePass = Mapper.mapEntryCodeToGatePass(generateResidentEntryCode);
        gatePass.setCode(RandomCodeGenerator.generateCode());
        gatePassRepository.save(gatePass);
        return Mapper.mapResidentEntryCodeToResponse(gatePass, residentRepository);

    }



    public ValidateCodeResponse validateCode(ValidateCodeRequest request){

            GatePass gatePass = gatePassRepository.findByCode(request.getCode())
                    .orElseThrow(() -> new InvalidGatePassException("Invalid code"));


            System.out.println(gatePass);
            return Mapper.mapGatePassToResponse(gatePass, residentRepository);

    }



    public String generateCode(){
        return RandomCodeGenerator.generateCode();
    }



    public String generateExitCode(String residentId){

        GatePass findGatePass = gatePassRepository
                .findById(residentId)
                .orElseThrow(() -> new GatePassDoesNotExistException("Id not found"));

        if(findGatePass.getPassType() == Type.EXIT){
            String code = generateCode();
            findGatePass.setCode(code);
            gatePassRepository.save(findGatePass);
            return "Exit Code has been generated";
        }
        return null;
    }



    public String extendTime(String code, String newEndTime){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
        GatePass findGatePass = gatePassRepository
                .findByCode(code)
                .orElseThrow(() -> new ResidentCodeDoesNotExitException("Cannot find code"));


        LocalDateTime endTime = LocalDateTime.parse(newEndTime, formatter);

        if(endTime.isBefore(LocalDateTime.now())){
            throw new InvalidTimeException("Time to be extended should be after current time");
        }

        findGatePass.setEndTime(endTime);
        gatePassRepository.save(findGatePass);
        return "Time extended successfully";

    }


}

