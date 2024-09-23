package br.edu.infnet.maintenanceservice.service.feign;

import br.edu.infnet.maintenanceservice.model.Mechanic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MECHANIC-SERVICE")
public interface MechanicClient {
    @GetMapping("/{mechanicId}")
    Mechanic getMechanicById(@PathVariable String mechanicId);
}