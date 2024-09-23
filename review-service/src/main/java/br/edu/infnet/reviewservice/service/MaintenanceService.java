package br.edu.infnet.reviewservice.service;

import br.edu.infnet.reviewservice.model.Maintenance;

public interface MaintenanceService {
    Maintenance getMaintenanceById(String maintenanceId);
}
