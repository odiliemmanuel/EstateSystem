package services;

import data.models.Resident;
import data.repositeries.ResidentRepository;
import dtos.requests.OnboardResidentRequest;
import dtos.responses.OnboardResidentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentManagementService {

    @Autowired
    private ResidentRepository residentRepository;

    public OnboardResidentResponse onboardResident(OnboardResidentRequest residentRequest){
        return null;
    }

    public String deleteResident(String id){
        return null;
    }

    public List<String> viewResidents(){
        return null;
    }

    public void validateCheckDuplicateFor(Resident resident){

    }

    public void disableResident(String id){
    }
}
