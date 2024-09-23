package br.edu.infnet.mechanicservice.exception;

public class InvalidMaintenanceDataException extends RuntimeException {
    public InvalidMaintenanceDataException(String message) {
        super(message);
    }
}