package br.edu.infnet.mechanicservice.service;

import br.edu.infnet.mechanicservice.model.Mechanic;

import java.util.List;
import java.util.Optional;

public interface MechanicService {
    List<Mechanic> getAllMechanics();
    Optional<Mechanic> getMechanicById(String id);
    List<Mechanic> getMechanicsByName(String name);
    Mechanic saveMechanic(Mechanic mechanic) ;
    boolean existsMechanicById(String id);
    Mechanic updateMechanicById(String id, Mechanic mechanic);
    void deleteMechanicById(String id);
    void processAverageRatingMechanic(String mechanicId);
}
