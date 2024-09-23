package br.edu.infnet.reviewservice.service.impl;

import br.edu.infnet.reviewservice.model.Review;
import br.edu.infnet.reviewservice.rabbitmq.ReviewProducer;
import br.edu.infnet.reviewservice.repository.ReviewRepository;
import br.edu.infnet.reviewservice.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewProducer reviewProducer;

    @Override
    public Review[] getAllReviewsByMechanicId(String mechanicId) {
        return reviewRepository.findAllByMechanicId(mechanicId);
    }

    @Override
    public int getReviewCountByMechanicId(String mechanicId) {
        return reviewRepository.countByMechanicId(mechanicId);
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public void sendReview(Review review) throws JsonProcessingException {
        reviewProducer.send(review);
    }


}
