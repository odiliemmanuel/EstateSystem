package data.repositeries;

import data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResidentRepository extends MongoRepository<Resident, String> {
    boolean validateByEmailOrPhoneNumber(String phoneNumber,  String email);

}
