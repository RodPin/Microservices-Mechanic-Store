package br.edu.infnet.mechanicservice.service.impl;

import br.edu.infnet.mechanicservice.exception.InvalidMechanicDataException;
import br.edu.infnet.mechanicservice.exception.MaintenanceNotFoundException;
import br.edu.infnet.mechanicservice.exception.MechanicNotFoundException;
import br.edu.infnet.mechanicservice.model.Mechanic;
import br.edu.infnet.mechanicservice.model.Average;
import br.edu.infnet.mechanicservice.model.Review;
import br.edu.infnet.mechanicservice.repository.MechanicRepository;
import br.edu.infnet.mechanicservice.service.MechanicService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;
    private final ReviewServiceImpl reviewService;

    @Override
    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    @Override
    public Optional<Mechanic> getMechanicById(String id) {
        return mechanicRepository.findById(id);
    }

    @Override
    public List<Mechanic> getMechanicsByName(String name) {
        return mechanicRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public boolean  existsMechanicById(String id) {
        return mechanicRepository.existsById(id);
    }

    @Override
    public Mechanic saveMechanic(Mechanic mechanic) {
        if (mechanic == null) {
            throw new InvalidMechanicDataException("Dados inválidos fornecidos para a criação do mecanico");
        }
        return mechanicRepository.save(mechanic);
    }

    @Override
    public Mechanic updateMechanicById(String id, Mechanic mechanic) {
        if (mechanic == null) {
            throw new InvalidMechanicDataException("Dados inválidos fornecidos para a atualização do mecanico");
        }
        return mechanicRepository.findById(id)
                .map(existingMechanic -> {
                    mechanic.setId(existingMechanic.getId());
                    return mechanicRepository.save(mechanic);
                })
                .orElseThrow(() -> new MechanicNotFoundException("Mecanico não encontrado para o ID: " + id));
    }

    @Override
    public void deleteMechanicById(String id) {
        Mechanic mechanic = mechanicRepository.findById(id)
                .orElseThrow(() -> new MechanicNotFoundException("Mecanico não encontrado para o ID: " + id));
        mechanicRepository.delete(mechanic);
    }

     @Override
    public void processAverageRatingMechanic(String mechanicId) {
        Optional<Mechanic> mechanic = this.getMechanicById(mechanicId);
        List<Review> reviews = reviewService.getAllReviewsByMechanicId(mechanicId);
        System.out.println("--------------------------------------------");
        System.out.println(reviews);
        if (mechanic.isPresent()) {
            double average = Average.calculateAverage(reviews);
            mechanic.ifPresent(mech -> {
                mech.setAverage(average);
            }); 
            mechanicRepository.save(mechanic.get());
        } else {
            throw new MaintenanceNotFoundException(mechanicId);
        }
    }
}

