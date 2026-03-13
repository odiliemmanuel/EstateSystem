package data.repositeries;

import data.models.GatePass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GatePassRepository extends MongoRepository<GatePass, String> {

    Optional<GatePass> findByCode(String code);
}
