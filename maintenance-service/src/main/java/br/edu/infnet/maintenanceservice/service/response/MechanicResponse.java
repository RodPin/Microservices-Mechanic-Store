package br.edu.infnet.maintenanceservice.service.response;

import br.edu.infnet.maintenanceservice.model.Mechanic;
import org.springframework.http.ResponseEntity;

public record MechanicResponse(ResponseEntity<Mechanic> mechanic) {
}
