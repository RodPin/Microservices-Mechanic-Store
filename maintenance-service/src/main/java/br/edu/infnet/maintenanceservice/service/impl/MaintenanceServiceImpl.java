package br.edu.infnet.maintenanceservice.service.impl;

import br.edu.infnet.maintenanceservice.exception.InvalidMaintenanceDataException;
import br.edu.infnet.maintenanceservice.exception.MaintenanceNotFoundException;
import br.edu.infnet.maintenanceservice.model.Maintenance;
import br.edu.infnet.maintenanceservice.repository.MaintenanceRepository;
import br.edu.infnet.maintenanceservice.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Override
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance saveMaintenance(Maintenance maintenance) {
        if (maintenance == null) {
            throw new InvalidMaintenanceDataException("Dados inválidos fornecidos para a criação da manutenção");
        }
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Optional<Maintenance> getMaintenanceById(String id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    public List<Maintenance> getMaintenancesByMechanicId(String mechanicId) {
        return maintenanceRepository.getMaintenancesByMechanicId(mechanicId);
    }

    @Override
    public List<Maintenance> getMaintenancesByTitle(String title) {
        return maintenanceRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void deleteMaintenanceById(String id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada para o ID: " + id));
        maintenanceRepository.delete(maintenance);
    }

    @Override
    public Maintenance updateMaintenance(String id, Maintenance updatedMaintenance) {
        Optional<Maintenance> maintenanceOptional = maintenanceRepository.findById(id);
        if (maintenanceOptional.isPresent()) {
            updatedMaintenance.setId(id);
            return maintenanceRepository.save(updatedMaintenance);
        }
        return null;
    }
}
