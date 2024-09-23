package br.edu.infnet.mechanicservice.service.response;

import br.edu.infnet.mechanicservice.model.Review;

import java.util.List;

public record ReviewResponse(List<Review> reviews) {
}
