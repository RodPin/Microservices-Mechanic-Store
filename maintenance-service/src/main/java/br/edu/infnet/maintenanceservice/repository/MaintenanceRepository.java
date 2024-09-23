package br.edu.infnet.maintenanceservice.repository;

import br.edu.infnet.maintenanceservice.model.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MaintenanceRepository extends MongoRepository<Maintenance, String> {
    List<Maintenance> getMaintenancesByMechanicId(String mechanicId);
    List<Maintenance> findByTitleContainingIgnoreCase(String title);
}
