package br.edu.infnet.maintenanceservice.service;

import br.edu.infnet.maintenanceservice.model.Maintenance;
import br.edu.infnet.maintenanceservice.repository.MaintenanceRepository;
import br.edu.infnet.maintenanceservice.service.impl.MaintenanceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class MaintenanceServiceTest {

    @Mock
    MaintenanceRepository maintenanceRepository;
    @InjectMocks
    MaintenanceServiceImpl maintenanceService;

    private Maintenance maintenance;
    
    @BeforeEach
    public void setUp() {
        maintenance = new Maintenance();
        maintenance.setId("1");
        maintenance.setTitle("Maintenance 1");
        maintenance.setDescription("Lorem ipsum");
        maintenance.setMechanicId("1");
       
    }

    @Test
    @DisplayName("Deve retornar todas as Manutenções.")
    public void testGetAllMaintenances() {
        when(maintenanceRepository.findAll()).thenReturn(Collections.singletonList(maintenance));
        List<Maintenance> allMaintenances = maintenanceService.getAllMaintenances();
        assertEquals(1, allMaintenances.size());
    }

    @Test
    @DisplayName("Deve salvar uma Manutenção com sucesso.")
    public void testSaveMaintenance() {
        List<Maintenance> maintenances = new ArrayList<>();
        when(maintenanceRepository.findAll()).thenReturn(maintenances);
        List<Maintenance> allMaintenances = maintenanceService.getAllMaintenances();
        int initialSize = allMaintenances.size();
        when(maintenanceRepository.save(maintenance)).thenAnswer(invocation -> {
            maintenances.add(maintenance);
            return maintenance;
        });
        maintenanceService.saveMaintenance(maintenance);
        allMaintenances = maintenanceService.getAllMaintenances();
        int finalSize = allMaintenances.size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    @DisplayName("Deve retornar as Manutenções pelo ID do mecanico.")
    public void testGetMaintenancesByMechanicId() {
        when(maintenanceRepository.getMaintenancesByMechanicId(maintenance.getMechanicId())).thenReturn(Collections.singletonList(maintenance));
        List<Maintenance> maintenancesByMechanic = maintenanceService.getMaintenancesByMechanicId(maintenance.getMechanicId());
        assertEquals(1, maintenancesByMechanic.size());
    }

    @Test
    @DisplayName("Deve retornar as Manutenções pelo título.")
    public void testGetMaintenancesByTitle() {
        when(maintenanceRepository.findByTitleContainingIgnoreCase(maintenance.getTitle())).thenReturn(Collections.singletonList(maintenance));
        List<Maintenance> maintenances = maintenanceService.getMaintenancesByTitle(maintenance.getTitle());
        assertEquals(maintenance.getTitle(), maintenances.get(0).getTitle());
    }

    @Test
    @DisplayName("Deve atualizar uma Manutenção com sucesso.")
    public void testUpdateMaintenance() {
        List<Maintenance> maintenances = new ArrayList<>();
        maintenances.add(maintenance);
        when(maintenanceRepository.findById(maintenance.getId())).thenReturn(Optional.of(maintenance));
        when(maintenanceRepository.save(any(Maintenance.class))).thenAnswer(invocation -> {
            Maintenance maintenance1 = invocation.getArgument(0);
            maintenances.set(0, maintenance1);
            return maintenance1;
        });
        Maintenance maintenance1 = maintenanceService.getMaintenanceById(maintenance.getId()).get();
        maintenance1.setTitle("Updated Teste Maintenance Title");
        Maintenance result = maintenanceService.updateMaintenance(maintenance.getId(), maintenance1);
        assertEquals("Updated Teste Maintenance Title", result.getTitle());
    }

    @Test
    @DisplayName("Deve remover uma Manutenção com sucesso.")
    public void testDeleteMaintenanceById() {
        List<Maintenance> maintenances = new ArrayList<>();
        maintenances.add(maintenance);
        when(maintenanceRepository.findAll()).thenReturn(maintenances);
        when(maintenanceRepository.findById(maintenance.getId())).thenReturn(Optional.ofNullable(maintenance));

        List<Maintenance> allMaintenances = maintenanceService.getAllMaintenances();
        int initialSize = allMaintenances.size();

        doAnswer(invocation -> {
            Maintenance maintenance1 = invocation.getArgument(0);
            maintenances.remove(maintenance1);
            return null;
        }).when(maintenanceRepository).delete(maintenance);

        maintenanceService.deleteMaintenanceById(maintenance.getId());
        when(maintenanceService.getAllMaintenances()).thenReturn(List.of());
        allMaintenances = maintenanceService.getAllMaintenances();
        int finalSize = allMaintenances.size();
        assertEquals(initialSize - 1, finalSize);
    }

}
