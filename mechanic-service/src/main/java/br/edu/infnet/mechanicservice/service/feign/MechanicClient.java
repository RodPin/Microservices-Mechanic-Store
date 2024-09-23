package br.edu.infnet.mechanicservice.service.feign;

import br.edu.infnet.mechanicservice.model.Mechanic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MECHANIC-SERVICE")
public interface MechanicClient {
    @GetMapping("/{mechanicId}")
    Mechanic getMechanicById(@PathVariable String mechanicId);
}

