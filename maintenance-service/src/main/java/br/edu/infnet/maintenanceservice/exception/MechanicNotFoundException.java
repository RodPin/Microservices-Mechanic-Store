package br.edu.infnet.maintenanceservice.exception;

public class MechanicNotFoundException extends RuntimeException {
    public MechanicNotFoundException(String message) {
        super(message);
    }
}