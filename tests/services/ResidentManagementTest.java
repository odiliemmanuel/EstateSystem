package services;

import com.realEstate.data.models.Resident;
import com.realEstate.data.repositeries.ResidentRepository;
import com.realEstate.dtos.requests.OnboardResidentRequest;
import com.realEstate.dtos.responses.OnboardResidentResponse;
import com.realEstate.services.ResidentManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ResidentManagementTest {


    ResidentRepository repository;


    @Test
    public void testResidentManagement() {
        Resident resident = new Resident();
        resident.setName("Resident");
        resident.setEmail("email");
        resident.setPhoneNumber("123456789");
        resident.setHouseAddress("houseAddress");

        OnboardResidentRequest  onboardResidentRequest = new OnboardResidentRequest();
        onboardResidentRequest.setName(resident.getName());
        onboardResidentRequest.setEmail(resident.getEmail());
        onboardResidentRequest.setPhoneNumber(resident.getPhoneNumber());
        onboardResidentRequest.setAddress(resident.getHouseAddress());

        OnboardResidentResponse onboardResidentResponse = new OnboardResidentResponse();
        onboardResidentResponse.setResidentEmail(resident.getEmail());
        onboardResidentResponse.setResidentName(resident.getName());
        onboardResidentResponse.setEnabled(resident.isEnabled());
        onboardResidentResponse.setDateRegistered(String.valueOf(resident.getDateRegistered()));

        ResidentManagementService services = new ResidentManagementService();
        services.onboardResident(onboardResidentRequest);


    }

}
