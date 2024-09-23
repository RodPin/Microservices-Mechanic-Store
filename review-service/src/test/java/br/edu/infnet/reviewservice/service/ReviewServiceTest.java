package br.edu.infnet.reviewservice.service;

import br.edu.infnet.reviewservice.model.Score;
import br.edu.infnet.reviewservice.model.Review;
import br.edu.infnet.reviewservice.rabbitmq.ReviewProducer;
import br.edu.infnet.reviewservice.repository.ReviewRepository;
import br.edu.infnet.reviewservice.service.impl.ReviewServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ReviewProducer reviewProducer;
    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review1;
    private Review review2;

    @BeforeEach
    public void setUp() {
        review1 = Review.builder()
                .id("1")
                .mechanicId("1")
                .comment("Awesome mechanic!")
                .score(5)
                .build();

        review2 = Review.builder()
                .id("2")
                .mechanicId("1")
                .comment("Awful job.")
                .score(2)
                .build();
    }

    @Test
    @DisplayName("Deve retornar todos os reviews para um ID de manutenção válido.")
    public void testGetAllReviewsByMechanicId() {
        Review[] allReviews = {review1, review2};
        when(reviewRepository.findAllByMechanicId("1")).thenReturn(allReviews);
        Review[] reviews = reviewService.getAllReviewsByMechanicId("1");
        assertEquals(2, reviews.length);
        assertEquals(review1.getComment(), reviews[0].getComment());
        assertEquals(review2.getComment(), reviews[1].getComment());
    }

    @Test
    @DisplayName("Deve salvar um review com sucesso.")
    public void testSaveReview() {
        when(reviewRepository.save(review1)).thenReturn(review1);
        Review savedReview = reviewService.saveReview(review1);
        assertEquals(review1.getComment(), savedReview.getComment());
    }

    @Test
    @DisplayName("Deve retornar a quantidade de Manutenções pelo ID.")
    public void testGetReviewCountByMechanicId() {
        String mechanic = "1";
        int expectedCount = 5;
        when(reviewRepository.countByMechanicId(mechanic)).thenReturn(expectedCount);
        int actualCount = reviewService.getReviewCountByMechanicId(mechanic);
        verify(reviewRepository).countByMechanicId(mechanic);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    @DisplayName("Deve enviar review com sucesso")
    public void testSendReview() throws JsonProcessingException {
        doNothing().when(reviewProducer).send(any(Review.class));
        reviewService.sendReview(review1);
        verify(reviewProducer, times(1)).send(review1);
    }
}
