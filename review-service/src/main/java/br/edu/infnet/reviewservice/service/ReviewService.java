package br.edu.infnet.reviewservice.service;

import br.edu.infnet.reviewservice.model.Review;

public interface ReviewService {
    Review saveReview(Review review);
    Review[] getAllReviewsByMechanicId(String mechanicId);
    int getReviewCountByMechanicId(String mechanicId);
}
