package br.edu.infnet.reviewservice.service.impl;

import br.edu.infnet.reviewservice.model.Maintenance;
import br.edu.infnet.reviewservice.service.MaintenanceService;
import br.edu.infnet.reviewservice.service.feign.MaintenanceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceClient maintenanceClient;
    @Override
    public Maintenance getMaintenanceById(String maintenanceId) {
        return maintenanceClient.getMaintenanceById(maintenanceId);
    }
}
