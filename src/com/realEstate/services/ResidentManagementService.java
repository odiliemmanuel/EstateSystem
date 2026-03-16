package com.realEstate.services;

import com.realEstate.data.models.Resident;
import com.realEstate.data.repositeries.ResidentRepository;
import com.realEstate.dtos.requests.OnboardResidentRequest;
import com.realEstate.dtos.responses.OnboardResidentResponse;
import com.realEstate.exceptions.ResidentAlreadyRegisteredException;
import com.realEstate.exceptions.ResidentDoesNotExistException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.realEstate.utils.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
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
       if(resident.isPresent()) residentRepository.delete(resident);

    }


    public List<String> viewResidents(){
        List<Resident> residents = residentRepository.findAll();
        List<String> listOfResidents = new ArrayList<>();
        for(Resident resident : residents){
            listOfResidents.add(resident.toString());
        }
        return listOfResidents;
    }


    public Resident viewResidentUsingMail(String email){
         if(!residentRepository.findByEmail(email).getEmail().equals(email)) throw new ResidentDoesNotExistException("Cannot find resident"); return residentRepository.findByEmail(email);

    }


    public void validateCheckDuplicateFor(Resident resident){
        if(residentRepository.findByEmail(resident.getEmail()).equals(resident)) throw new ResidentAlreadyRegisteredException("Resident already exists");

    }

    public void disableResident(String id){
        Resident resident = residentRepository.findById(id).get();
        resident.setEnabled(false);

    }
}
