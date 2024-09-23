package br.edu.infnet.maintenanceservice.service.impl;

import br.edu.infnet.maintenanceservice.model.Mechanic;
import br.edu.infnet.maintenanceservice.service.MechanicService;
import br.edu.infnet.maintenanceservice.service.feign.MechanicClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MechanicServiceImpl implements MechanicService {

    private final MechanicClient mechanicClient;

    @Override
    public Mechanic getMechanicById(String id) {
        return mechanicClient.getMechanicById(id);
    }
}
