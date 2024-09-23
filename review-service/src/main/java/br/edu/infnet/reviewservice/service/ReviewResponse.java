package br.edu.infnet.reviewservice.service;

import br.edu.infnet.reviewservice.model.Review;

import java.util.List;

public record ReviewResponse(List<Review> reviews) {
}
