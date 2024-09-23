package br.edu.infnet.mechanicservice.rabbitmq;

import br.edu.infnet.mechanicservice.model.Review;
import br.edu.infnet.mechanicservice.service.impl.MechanicServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewConsumer {

    private final MechanicServiceImpl mechanicService;

    @RabbitListener(queues = {"review-queue"})
    public void receiveReview(@Payload String message) throws JsonProcessingException {
       try {
        Review review = new ObjectMapper().readValue(message, Review.class);
        mechanicService.processAverageRatingMechanic(review.getMechanicId());

    } catch (JsonProcessingException e) {
        System.err.println("Error processing review message: {}"+ message+ e);
    } catch (Exception e) {
        System.err.println("An unexpected error occurred: {}"+ message+ e);
    }
    }
}
