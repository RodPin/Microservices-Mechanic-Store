package br.edu.infnet.mechanicservice.repository;

import br.edu.infnet.mechanicservice.model.Mechanic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MechanicRepository extends MongoRepository<Mechanic, String> {
    List<Mechanic> findByNameContainingIgnoreCase(String mechanicName);
}