package services;

import data.models.GatePass;
import data.models.Resident;
import data.repositeries.GatePassRepository;
import data.repositeries.ResidentRepository;
import dtos.requests.GenerateResidentEntryCodeRequest;
import dtos.requests.GenerateVisitorEntryCodeRequest;
import dtos.requests.ValidateCodeRequest;
import dtos.responses.GenerateResidentEntryCodeResponse;
import dtos.responses.GenerateVisitorEntryCodeResponse;
import dtos.responses.ValidateCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public String generateExitCode(String code, String residentId){
        return null;
    }

    public GenerateVisitorEntryCodeResponse generateVisitorEntryCode(GenerateVisitorEntryCodeRequest visitorEntryCode){
        return null;
    }

    public GenerateResidentEntryCodeResponse generateResidentEntryCode(GenerateResidentEntryCodeRequest residentEntryCode){
        return null;
    }

    public ValidateCodeResponse validateCode(ValidateCodeRequest validateCodeRequest){
        return null;
    }

    public String generateCode(){
        return RandomCodeGenerator.generateCode();
    }

    public

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
