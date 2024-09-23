package br.edu.infnet.maintenanceservice.exception;

public class InvalidMaintenanceDataException extends RuntimeException {
    public InvalidMaintenanceDataException(String message) {
        super(message);
    }
}