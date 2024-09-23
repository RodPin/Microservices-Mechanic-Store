package br.edu.infnet.maintenanceservice.controller;

import br.edu.infnet.maintenanceservice.exception.InvalidMaintenanceDataException;
import br.edu.infnet.maintenanceservice.exception.MaintenanceNotFoundException;
import br.edu.infnet.maintenanceservice.exception.MechanicNotFoundException;
import br.edu.infnet.maintenanceservice.model.Maintenance;
import br.edu.infnet.maintenanceservice.model.Mechanic;
import br.edu.infnet.maintenanceservice.service.impl.MaintenanceServiceImpl;
import br.edu.infnet.maintenanceservice.service.impl.MechanicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class MaintenanceController {

    private final MaintenanceServiceImpl maintenanceService;
    private final MechanicServiceImpl mechanicService;

    @GetMapping
    @Operation(summary = "Retorna todas as Manutenções", description = "Este endpoint retorna uma lista de todas as Manutenções disponíveis. Se não houver nenhuma manutenção, retornará 404 Not Found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenções retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma manutenção encontrada")
    })
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        if (maintenances.isEmpty()) {
            throw new MaintenanceNotFoundException("Nenhuma manutenção encontrada");
        }
        return ResponseEntity.ok(maintenances);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova manutenção", description = "Este endpoint cria uma nova manutenção com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Manutenção criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a criação da manutenção"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado")
    })
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
        Mechanic mechanic = mechanicService.getMechanicById(maintenance.getMechanicId());
        if (mechanic == null) {
            throw new MechanicNotFoundException("Nenhum mecanico encontrado para o ID: " + maintenance.getMechanicId());
        }
        Maintenance newMaintenance = maintenanceService.saveMaintenance(maintenance);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMaintenance);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma manutenção por ID", description = "Este endpoint retorna uma manutenção específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenção retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada")
    })
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable String id) {
        log.info("Get Maintenance by ID: {}", id);
        return maintenanceService.getMaintenanceById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MaintenanceNotFoundException("Manutenção não encontrada para o ID: " + id));
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Retorna todas as Manutenções pelo título", description = "Este endpoint retorna todas Manutenções com base no título fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenções retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhuma manutenção encontrada")
    })
    public ResponseEntity<List<Maintenance>> getMaintenanceByTitle(@PathVariable String title) {
        List<Maintenance> maintenances = maintenanceService.getMaintenancesByTitle(title);
        if (maintenances.isEmpty()) {
            throw new MaintenanceNotFoundException("Nenhuma manutenção encontrada para o title: " + title);
        }
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/mechanicMaintenances/{id}")
    @Operation(summary = "Retorna todas as Manutenções de um mecanico", description = "Este endpoint retorna uma lista de todas as Manutenções associadas a um mecanico específico com base no ID do mecanico fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenções retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum mecanico encontrado ou nenhuma manutenção associada a ele encontrada")
    })
    public ResponseEntity<List<Maintenance>> getAllMaintenancesByMechanic(@PathVariable  String id) {
        List<Maintenance> maintenances = maintenanceService.getMaintenancesByMechanicId(id);
        if (maintenances.isEmpty()) {
            throw new MaintenanceNotFoundException("Nenhuma manutenção encontrada para o mecanico com ID: " + id);
        }

        return ResponseEntity.ok(maintenances);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma manutenção existente", description = "Este endpoint atualiza uma manutenção específica com base no ID fornecido e nos novos dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Manutenção atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a atualização")
    })
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable String id, @RequestBody Maintenance updatedMaintenance) {
        try {
            Maintenance maintenance = maintenanceService.updateMaintenance(id, updatedMaintenance);
            if (maintenance == null) {
                throw new MaintenanceNotFoundException("Manutenção não encontrada para o ID: " + id);
            }
            return ResponseEntity.ok(maintenance);
        } catch (InvalidMaintenanceDataException ex) {
            throw new InvalidMaintenanceDataException("Dados inválidos fornecidos para a atualização");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma manutenção por ID", description = "Este endpoint exclui uma manutenção específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Manutenção excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada")
    })
    public ResponseEntity<Map<String, String>> deleteMaintenanceById(@PathVariable String id) {
        Map<String, String> response = new HashMap<>();
        try {
            maintenanceService.deleteMaintenanceById(id);
            response.put("message", "Manutenção excluída com sucesso");
            return ResponseEntity.ok(response);
        } catch (MaintenanceNotFoundException ex) {
            throw new MaintenanceNotFoundException("Manutenção não encontrada para o ID: " + id);
        }
    }
}
