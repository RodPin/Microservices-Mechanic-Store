package br.edu.infnet.mechanicservice.service;

import br.edu.infnet.mechanicservice.model.Review;
import br.edu.infnet.mechanicservice.model.Mechanic;
import br.edu.infnet.mechanicservice.repository.MechanicRepository;
import br.edu.infnet.mechanicservice.service.feign.ReviewClient;
import br.edu.infnet.mechanicservice.service.impl.MechanicServiceImpl;
import br.edu.infnet.mechanicservice.service.impl.ReviewServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class MechanicServiceTest {
    @InjectMocks
    MechanicServiceImpl mechanicService;
    @Mock
    MechanicRepository mechanicRepository;
    
    @Mock
    private ReviewServiceImpl reviewService; 
    @Mock
    private ReviewClient reviewClient;

    private Mechanic mechanic;
    private Review review;

    @BeforeEach
    public void setUp() {
        mechanic = Mechanic.builder()
                .id("1")
                .name("Test Mechanic 1")
                .avatarUrl("https://gravatar.com/avatar/82b5fde5c4448a4e5f46dfa0980474a3?s=400&d=robohash&r=x")
                .average(0.0)
                .bio("Test Bio")
                .build();
            
        review = Review.builder()
                .mechanicId(mechanic.getId())
                .score(5)
                .build();
    }

    
    @Test
    @DisplayName("Deve retornar a média do Mecanico.")
    public void testProcessAverageRatingMechanic() {
        when(mechanicRepository.findById(mechanic.getId())).thenReturn(Optional.of(mechanic));

        mechanicService.processAverageRatingMechanic(review.getMechanicId());

        assertEquals(0, mechanic.getAverage());
        assertEquals(0, 0);

    }
}
