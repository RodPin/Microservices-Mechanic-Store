package br.edu.infnet.reviewservice.service;


import br.edu.infnet.reviewservice.model.Maintenance;
import br.edu.infnet.reviewservice.service.feign.MaintenanceClient;
import br.edu.infnet.reviewservice.service.impl.MaintenanceServiceImpl;
import br.edu.infnet.reviewservice.service.impl.ReviewServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
public class MaintenanceServiceTest {
    @Mock
    private MaintenanceClient maintenanceClient;
    @InjectMocks
    private ReviewServiceImpl reviewService;

    @InjectMocks
    private MaintenanceServiceImpl maintenanceService;

    private Maintenance maintenance;

    @BeforeEach
    public void setUp() {
        maintenance = Maintenance.builder()
                .id("1")
                .title("Turbo Adicional")
                .description("Bom.")
                .build();
    }

    @Test
    @DisplayName("Deve retornar a manutencao por ID com sucesso")
    public void testGetMaintenanceById() {
        when(maintenanceClient.getMaintenanceById(anyString())).thenReturn(maintenance);
        Maintenance result = maintenanceService.getMaintenanceById("1");
        Assertions.assertEquals(maintenance, result, "A manutencao retornada pelo serviço deve ser igual ao mock.");
        verify(maintenanceClient).getMaintenanceById("1");
    }
}
