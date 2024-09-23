package br.edu.infnet.reviewservice.rabbitmq;

import br.edu.infnet.reviewservice.model.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    public void send(Review review) throws JsonProcessingException {
        amqpTemplate.convertAndSend("review-exc", "review-rk", objectMapper.writeValueAsString(review));
    }
}
