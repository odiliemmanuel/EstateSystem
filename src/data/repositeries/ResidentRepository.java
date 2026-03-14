package data.repositeries;

import data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ResidentRepository extends MongoRepository<Resident, String> {
    boolean validateByEmailOrPhoneNumber(String phoneNumber,  String email);
    void delete(Optional<Resident> resident);
}
