package com.realEstate.services;

import com.realEstate.data.models.GatePass;
import com.realEstate.data.models.Type;
import com.realEstate.data.repositeries.GatePassRepository;
import com.realEstate.data.repositeries.ResidentRepository;
import com.realEstate.dtos.requests.GenerateResidentEntryCodeRequest;
import com.realEstate.dtos.requests.GenerateVisitorEntryCodeRequest;
import com.realEstate.dtos.requests.ValidateCodeRequest;
import com.realEstate.dtos.responses.GenerateResidentEntryCodeResponse;
import com.realEstate.dtos.responses.GenerateVisitorEntryCodeResponse;
import com.realEstate.dtos.responses.ValidateCodeResponse;
import com.realEstate.exceptions.GatePassDoesNotExistException;
import com.realEstate.exceptions.InvalidTimeException;
import com.realEstate.exceptions.ResidentCodeDoesNotExitException;
import com.realEstate.exceptions.ResidentDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.realEstate.utils.Mapper;
import com.realEstate.utils.RandomCodeGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Service
public class GatePassAccessService {


    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private GatePassRepository gatePassRepository;


    public String disableCode(String phoneNumber, String emailAddress) {

        if(residentRepository.validateByEmailOrPhoneNumber(phoneNumber, emailAddress)){

            residentRepository.findByEmail(emailAddress).setEnabled(false);
            return "Gate pass has been disabled";
        }
        else{
            throw new ResidentDoesNotExistException("Cannot find resident");
        }

    }


    public String generateExitCode(String phoneNumber, String emailAddress){
        if(residentRepository.validateByEmailOrPhoneNumber(phoneNumber, emailAddress)){
            RandomCodeGenerator.generateCode();
            return "Pass Code successfully generated";
        }
        else{
            throw new ResidentDoesNotExistException("Resident does not exist");
        }

    }



    public GenerateVisitorEntryCodeResponse generateVisitorEntryCode(GenerateVisitorEntryCodeRequest visitorEntryCode){

        GatePass gatepass = Mapper.mapVisitor(visitorEntryCode);
        gatePassRepository.save(gatepass);
        return Mapper.mapVisitorToResponse(gatepass);

    }


    public GenerateResidentEntryCodeResponse generateResidentEntryCode(GenerateResidentEntryCodeRequest generateResidentEntryCode) {

        GatePass gatePass = Mapper.mapEntryCodeToGatePass(generateResidentEntryCode);
        gatePassRepository.save(gatePass);
        return Mapper.mapResidentEntryCodeToResponse(gatePass);

    }



    public ValidateCodeResponse validateCodeResponse(ValidateCodeRequest validateCodeRequest){
        GatePass gatePass = Mapper.mapValidateCodeToGatePass(validateCodeRequest);
        gatePassRepository.save(gatePass);
        return Mapper.mapGatePassToResponse(gatePass);

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


//        OnboardResidentResponse onboardResidentResponse = new OnboardResidentResponse();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
//        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
//        onboardResidentResponse.setResidentId(residentId);
//        onboardResidentResponse.setEndTime(endDateTime);
//        return null;