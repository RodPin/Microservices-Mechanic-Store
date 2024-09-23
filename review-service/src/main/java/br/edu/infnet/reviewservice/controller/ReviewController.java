package br.edu.infnet.reviewservice.controller;

import br.edu.infnet.reviewservice.model.Review;
import br.edu.infnet.reviewservice.model.Mechanic;
import br.edu.infnet.reviewservice.service.ReviewResponse;
import br.edu.infnet.reviewservice.service.impl.ReviewServiceImpl;
import br.edu.infnet.reviewservice.service.impl.MechanicServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    private final MechanicServiceImpl mechanicService;

    @PostMapping
    @Operation(summary = "Salva um review",
            description = "Este endpoint salva um review associado a uma manutenção, recebendo o review e o Score.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review salvo com sucesso"),
            @ApiResponse(responseCode = "400", description = "Score inválido ou manutenção não encontrada"),
            @ApiResponse(responseCode = "404", description = "Manutenção não encontrada")
    })
    public ResponseEntity<?> saveReview(@RequestBody Review review, @RequestParam String score) throws JsonProcessingException {
        log.info("Save Review: {}", review);
        log.info("Score: {}", score);
        Mechanic mechanic = mechanicService.getMechanicById(review.getMechanicId());
        if (mechanic == null) {
            return ResponseEntity.notFound().build();
        }
        if (!Review.isValidScore(score)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid score"));
        }

        review.setScore(Double.parseDouble(score));
        Review reviewSaved = reviewService.saveReview(review);
        reviewService.sendReview(reviewSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewSaved);
    }

    @GetMapping("/mechanic/{mechanicId}")
    @Operation(summary = "Recupera todos os reviews de um mecanico específica",
            description = "Este endpoint recupera todos os reviews associados a um ID de um mecanico específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews recuperados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum review encontrado para o ID de um mecanico fornecido")
    })
    public ResponseEntity<ReviewResponse> getAllReviewsByMechanicId(@PathVariable String mechanicId) {
        log.info("Get Reviews by Mechanic ID: {}", mechanicId);
        Review[] reviews = reviewService.getAllReviewsByMechanicId(mechanicId);
        ReviewResponse reviewResponse = new ReviewResponse(Arrays.asList(reviews));
        return reviews.length == 0 ? ResponseEntity.notFound().build() : ResponseEntity.ok(reviewResponse);
    }
}
