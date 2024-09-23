package br.edu.infnet.reviewservice.model;

public class Score {
    private double value;

    public Score(){
        
    }
    public Score(double value) {
        this.value = value;
    }

    public Score(String value) {
        this.value = Double.parseDouble(value);
    }

    public double getValue() {
        return value;
    }
}