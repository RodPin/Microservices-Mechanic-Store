package br.edu.infnet.reviewservice.service.feign;

import br.edu.infnet.reviewservice.model.Maintenance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MAINTENANCE-SERVICE")
public interface MaintenanceClient {
    @GetMapping("/{maintenanceId}")
    Maintenance getMaintenanceById(@PathVariable String maintenanceId);
}
