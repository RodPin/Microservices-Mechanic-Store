package br.edu.infnet.maintenanceservice.exception;

public class MaintenanceNotFoundException extends RuntimeException {
    public MaintenanceNotFoundException(String message) {
        super(message);
    }
}