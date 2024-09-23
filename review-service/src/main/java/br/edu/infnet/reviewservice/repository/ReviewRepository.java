package br.edu.infnet.reviewservice.repository;

import br.edu.infnet.reviewservice.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
    Review[] findAllByMechanicId(String mechanicId);
    int countByMechanicId(String mechanicId);
}
