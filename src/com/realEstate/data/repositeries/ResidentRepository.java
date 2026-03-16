package com.realEstate.data.repositeries;

import com.realEstate.data.models.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidentRepository extends MongoRepository<Resident, String> {

    Resident findByEmail(String email);
    boolean existResidentByEmailOrPhoneNumber(String phoneNumber, String emailAddress);

}