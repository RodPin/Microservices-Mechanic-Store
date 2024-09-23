package br.edu.infnet.mechanicservice.service.impl;

import java.util.List;
import br.edu.infnet.mechanicservice.model.Review;
import br.edu.infnet.mechanicservice.service.ReviewService; // Import the interface
import br.edu.infnet.mechanicservice.service.feign.ReviewClient;
import br.edu.infnet.mechanicservice.service.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService { 
    private final ReviewClient reviewClient;

    @Override
    public List<Review> getAllReviewsByMechanicId(String mechanicId) {
        ReviewResponse reviewResponse = reviewClient.getAllReviewsByMechanicId(mechanicId);
        List<Review> reviews = reviewResponse.reviews();
        return reviews;
    }
}