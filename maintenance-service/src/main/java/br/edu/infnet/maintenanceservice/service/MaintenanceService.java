package br.edu.infnet.maintenanceservice.service;

import br.edu.infnet.maintenanceservice.model.Maintenance;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {
    List<Maintenance> getAllMaintenances();
    Maintenance saveMaintenance(Maintenance maintenance);
    Optional<Maintenance> getMaintenanceById(String id);
    List<Maintenance> getMaintenancesByMechanicId(String mechanicId);
    List<Maintenance> getMaintenancesByTitle(String title);
    void deleteMaintenanceById(String id) ;
    Maintenance updateMaintenance(String id, Maintenance updatedMaintenance);
}
