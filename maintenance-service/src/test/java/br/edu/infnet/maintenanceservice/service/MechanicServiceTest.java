package br.edu.infnet.maintenanceservice.service;

import br.edu.infnet.maintenanceservice.model.Mechanic;
import br.edu.infnet.maintenanceservice.service.feign.MechanicClient;
import br.edu.infnet.maintenanceservice.service.impl.MechanicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MechanicServiceTest {
    @Mock
    MechanicClient mechanicClient;
    @InjectMocks
    MechanicServiceImpl mechanicService;
    Mechanic mechanic;

    @BeforeEach
    public void setUp() {
        mechanic = Mechanic.builder()
                .id("1")
                .name("Test Mechanic")
                .avatarUrl("http://example.com/avatar")
                .bio("Test Bio")
                .build();
    }

    @Test
    @DisplayName("Deve retornar um mecanico ao buscar por ID")
    public void testGetMechanicById() {
        when(mechanicClient.getMechanicById("1")).thenReturn(mechanic);
        Mechanic result = mechanicService.getMechanicById("1");
        assertEquals(result.getId(), mechanic.getId());
    }
}
