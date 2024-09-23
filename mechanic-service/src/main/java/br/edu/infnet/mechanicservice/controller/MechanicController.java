package br.edu.infnet.mechanicservice.controller;

import br.edu.infnet.mechanicservice.exception.InvalidMechanicDataException;
import br.edu.infnet.mechanicservice.exception.MechanicNotFoundException;
import br.edu.infnet.mechanicservice.model.Mechanic;
import br.edu.infnet.mechanicservice.service.impl.MechanicServiceImpl;
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
@RequestMapping("/")
@Slf4j
public class MechanicController {

    private final MechanicServiceImpl mechanicService;

    @GetMapping
    @Operation(summary = "Retorna todos os Mecanicos", description = "Este endpoint retorna uma lista de todos os Mecanicos disponíveis. Se não houver nenhum mecanico, retornará 404 Not Found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mecanicos retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum mecanico encontrado")
    })
    public ResponseEntity<List<Mechanic>> getAllMechanics() {
        List<Mechanic> mechanics = mechanicService.getAllMechanics();
        if (mechanics.isEmpty()) {
            throw new MechanicNotFoundException("Nenhum mecanico encontrada");
        }
        return ResponseEntity.ok(mechanics);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um mecanico por ID", description = "Este endpoint retorna um mecanico específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mecanico retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado")
    })
    public ResponseEntity<Mechanic> getMechanicById(@PathVariable String id) {
        log.info("Get Mechanic by ID: {}", id);
        return mechanicService.getMechanicById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new MechanicNotFoundException("Nenhum mecanico encontrado para o ID: " + id));
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Busca os mecanico pelo nome", description = "Este endpoint retorna todos Mecanicos com base no nome fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mecanicos retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mecanicos não encontrado")
    })
    public ResponseEntity<List<Mechanic>> getMechanicsByName(@PathVariable String name) {
        log.info("Get Maintenance by Name: {}", name);
        List<Mechanic> mechanics = mechanicService.getMechanicsByName(name);
        if(mechanics.isEmpty()) {
            throw new MechanicNotFoundException("Nenhum mecanico encontrado com o nome " + name);
        }

        return ResponseEntity.ok(mechanics);
    }

    @PostMapping
    @Operation(summary = "Cria um novo mecanico", description = "Este endpoint cria um novo mecanico com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mecanico criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a criação do mecanico"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado")
    })
    public ResponseEntity<Mechanic> createMechanic(@RequestBody Mechanic mechanic) {
        Mechanic newMechanic = mechanicService.saveMechanic(mechanic);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMechanic);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um mecanico existente", description = "Este endpoint atualiza um mecanico específico com base no ID fornecido e nos novos dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mecanico atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a atualização")
    })
    public ResponseEntity<Mechanic> updateMechanic(@PathVariable String id, @RequestBody Mechanic mechanic) {
        try {
            Mechanic updatedMechanic = mechanicService.updateMechanicById(id, mechanic);
            if (updatedMechanic == null) {
                throw new MechanicNotFoundException("Mecanico não encontrado para o ID: " + id);
            }
            return ResponseEntity.ok(updatedMechanic);
        } catch (InvalidMechanicDataException ex) {
            throw new InvalidMechanicDataException("Dados inválidos fornecidos para a atualização");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um mecanico por ID", description = "Este endpoint exclui um mecanico específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mecanico excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mecanico não encontrado")
    })
    public ResponseEntity<Map<String, String>> deleteMechanicById(@PathVariable String id) {
        Map<String, String> response = new HashMap<>();
        try {
            mechanicService.deleteMechanicById(id);
            response.put("message", "Mecanico excluída com sucesso");
            return ResponseEntity.ok(response);
        } catch (MechanicNotFoundException ex) {
            throw new MechanicNotFoundException("Mecanico não encontrada para o ID: " + id);
        }
    }
}
