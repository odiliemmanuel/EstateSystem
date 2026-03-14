package utils;

import data.models.Resident;
import dtos.requests.OnboardResidentRequest;
import dtos.responses.OnboardResidentResponse;

public class Mapper {
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

}
