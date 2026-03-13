package services;

import data.repositeries.GatePassRepository;
import data.repositeries.ResidentRepository;
import dtos.requests.GenerateVisitorEntryCodeRequest;
import dtos.responses.GenerateVisitorEntryCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateAccessService {

    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private GatePassRepository gatePassRepository;


    public String disableCode(String code, String residentId) {
       return null;

    }

    public String generateExitCode(String code, String residentId){
        return null;
    }

    public GenerateVisitorEntryCodeResponse generateVisitorEntryCode(GenerateVisitorEntryCodeRequest visitorEntryCode){
        return null;
    }
}
