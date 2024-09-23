package br.edu.infnet.mechanicservice.service;

import br.edu.infnet.mechanicservice.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsByMechanicId(String mechanicId);
}