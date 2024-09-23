package br.edu.infnet.mechanicservice.service.response;

import br.edu.infnet.mechanicservice.model.Mechanic;
import org.springframework.http.ResponseEntity;

public record MechanicResponse(ResponseEntity<Mechanic> mechanic) {
}
