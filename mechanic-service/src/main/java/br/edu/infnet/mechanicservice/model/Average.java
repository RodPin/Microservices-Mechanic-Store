package br.edu.infnet.mechanicservice.model;
import java.util.List;

public class Average {
    private double value;

    public Average(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static double calculateAverage(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        double totalScore = 0.0;
        int totalReviews = reviews.size();

        for (Review review : reviews) {
            totalScore += (double) review.getScore();
        }

        double averageValue = totalScore / totalReviews;
        return averageValue;
    }
}