package br.edu.infnet.mechanicservice.service.feign;

import br.edu.infnet.mechanicservice.model.Review;
import br.edu.infnet.mechanicservice.service.response.ReviewResponse;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("REVIEW-SERVICE")
public interface ReviewClient {
    @GetMapping("/mechanic/{mechanicId}")
    ReviewResponse getAllReviewsByMechanicId(@PathVariable String mechanicId);
}
