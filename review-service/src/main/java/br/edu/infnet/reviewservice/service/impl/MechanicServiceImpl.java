package br.edu.infnet.reviewservice.service.impl;

import br.edu.infnet.reviewservice.model.Mechanic;
import br.edu.infnet.reviewservice.service.MechanicService;
import br.edu.infnet.reviewservice.service.feign.MechanicClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MechanicServiceImpl implements MechanicService {
    private final MechanicClient mechanicClient;
    @Override
    public Mechanic getMechanicById(String mechanicId) {
        return mechanicClient.getMechanicById(mechanicId);
    }
}
