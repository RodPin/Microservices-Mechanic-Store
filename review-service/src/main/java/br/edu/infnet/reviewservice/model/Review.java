package br.edu.infnet.reviewservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String mechanicId;
    private String comment;
    private double score;
    
    public static boolean isValidScore(String value) {
        try {
            double number = Double.parseDouble(value);
            return number >= 0 && number <= 5; 
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}


