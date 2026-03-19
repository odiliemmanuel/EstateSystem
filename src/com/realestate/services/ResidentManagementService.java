package com.realestate.services;

import com.realestate.data.models.Resident;
import com.realestate.data.repositeries.ResidentRepository;
import com.realestate.dtos.requests.OnboardResidentRequest;
import com.realestate.dtos.responses.OnboardResidentResponse;
import com.realestate.exceptions.ResidentAlreadyRegisteredException;
import com.realestate.exceptions.ResidentDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.realestate.utils.Mapper;
import java.util.ArrayList;
import java.util.List;



@Service
public class ResidentManagementService {

    @Autowired
    private ResidentRepository residentRepository;

    public OnboardResidentResponse onboardResident(OnboardResidentRequest residentRequest){
       Resident resident = Mapper.mapToResident(residentRequest);
        residentRepository.save(resident);
        return Mapper.mapResidentToResponse(resident);
    }


    public void deleteResident(String emailAddress){
       Resident resident = residentRepository.findByEmail(emailAddress);
       if(resident.getEmail().equals(emailAddress)){
           resident.setEnabled(false);
           residentRepository.deleteByEmail(emailAddress);
       }
       else{
           throw new ResidentDoesNotExistException("Resident does not exist");
       }

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
