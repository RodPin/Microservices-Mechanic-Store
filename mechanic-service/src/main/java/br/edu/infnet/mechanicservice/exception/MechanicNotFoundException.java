package br.edu.infnet.mechanicservice.exception;

public class MechanicNotFoundException extends RuntimeException {
    public MechanicNotFoundException(String message) {
        super(message);
    }
}