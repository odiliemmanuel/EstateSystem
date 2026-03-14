package services;

import data.models.GatePass;
import data.models.Type;
import data.models.Visitor;
import data.repositeries.GatePassRepository;
import data.repositeries.ResidentRepository;
import dtos.requests.GenerateResidentEntryCodeRequest;
import dtos.requests.GenerateVisitorEntryCodeRequest;
import dtos.requests.ValidateCodeRequest;
import dtos.responses.GenerateResidentEntryCodeResponse;
import dtos.responses.GenerateVisitorEntryCodeResponse;
import dtos.responses.OnboardResidentResponse;
import dtos.responses.ValidateCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Mapper;
import utils.RandomCodeGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class GateAccessService {

    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private GatePassRepository gatePassRepository;


    public String disableCode(String code, String residentId) {
        Optional<GatePass> findGatePass = gatePassRepository.findByCode(code);
        if (findGatePass.isPresent()){
            GatePass gatePass = findGatePass.get();
            gatePass.setValid(false);
            return "Gate pass has been disabled";
        }
       return null;

    }

    public String generateExitCode(String code, String codeType){

        Optional<GatePass> findGatePass = gatePassRepository.findByCode(code);

        return null;
    }



    public GenerateVisitorEntryCodeResponse generateVisitorEntryCode(GenerateVisitorEntryCodeRequest visitorEntryCode){

        GatePass gatepass = Mapper.mapVisitor(visitorEntryCode);
        gatePassRepository.save(gatepass);
        return Mapper.mapGatePassToResponse(gatepass);

    }



    public GenerateResidentEntryCodeResponse generateResidentEntryCode(GenerateResidentEntryCodeRequest generateResidentEntryCode){

        GatePass gatePass = Mapper.mapEntryCodeToGatePass(generateResidentEntryCode);
        gatePassRepository.save(gatePass);
        return Mapper.mapResidentEntryCodeToResponse(gatePass);

    }

//    private String codeType;
//    private String code;
    public ValidateCodeResponse validateCode(ValidateCodeRequest validateCodeRequest){

        return null;
    }

    public GatePass mapTo(ValidateCodeRequest validateCodeRequest){
        GatePass gatePass = new GatePass();
    }


    public String generateCode(){

        GatePass gatePass = new GatePass();
        if (gatePass.getPassType() == Type.ENTRY){
            return RandomCodeGenerator.generateCode();
        }
        return null;
    }



    public String generateExitCode(String code){

        Optional<GatePass> findGatePass = gatePassRepository.findByCode(code);
        GatePass gatePass = findGatePass.get();
        if(gatePass.getPassType() == Type.EXIT){
            return RandomCodeGenerator.generateCode();
        }
        return null;
    }



    public String extendTime(String code, String newEndTime){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
        Optional<GatePass> findGatePass = gatePassRepository.findByCode(code);

        if(findGatePass.isPresent()){
            GatePass gatePass = findGatePass.get();
            LocalDateTime oldEndTime = gatePass.getEndTime();
            LocalDateTime endTime = LocalDateTime.parse(newEndTime, formatter);

            if(endTime.isBefore(oldEndTime)){
                return "Cannot extend to a date before the starting date dummy";
            }

            gatePass.setEndTime(endTime);
            gatePassRepository.save(gatePass);
            return "Time extended successfully";
        }
        return null;
    }
}


//        OnboardResidentResponse onboardResidentResponse = new OnboardResidentResponse();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
//        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
//        onboardResidentResponse.setResidentId(residentId);
//        onboardResidentResponse.setEndTime(endDateTime);
//        return null;