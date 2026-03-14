package services;

import data.models.Resident;
import data.repositeries.ResidentRepository;
import dtos.requests.OnboardResidentRequest;
import dtos.responses.OnboardResidentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Mapper;
import java.util.List;
import java.util.Optional;


@Service
public class ResidentManagementService {

    @Autowired
    private ResidentRepository residentRepository;

    public OnboardResidentResponse onboardResident(OnboardResidentRequest residentRequest){
       Resident resident = Mapper.mapToResident(residentRequest);
        residentRepository.save(resident);
        return Mapper.mapResidentToResponse(resident);
    }


    public void deleteResident(String id){
       Optional<Resident> resident = residentRepository.findById(id);
       if(resident.isPresent()){
           residentRepository.delete(resident);
       }

    }

    public List<Resident> viewResidents(){
        return residentRepository.findAll();
    }


    public void validateCheckDuplicateFor(Resident resident){
        if(residentRepository.findById(resident.getId()).isPresent()){
            residentRepository.deleteById(resident.getId());
            residentRepository.save(resident);
        }
    }

    public void disableResident(String id){
        Resident resident = residentRepository.findById(id).get();
        resident.setEnabled(false);

    }
}
