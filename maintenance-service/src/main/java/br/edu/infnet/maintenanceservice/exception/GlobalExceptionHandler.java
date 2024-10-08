package br.edu.infnet.maintenanceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaintenanceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMaintenanceNotFoundException(MaintenanceNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidMaintenanceDataException.class)
    public ResponseEntity<Map<String, String>> handleInvalidMaintenanceDataException(InvalidMaintenanceDataException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

//    @ExceptionHandler(InvalidMechanicDataException.class)
//    public ResponseEntity<Map<String, String>> handleInvalidMechanicDataException(InvalidMechanicDataException ex) {
//        Map<String, String> errorResponse = new HashMap<>();
//        errorResponse.put("message", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//    }

    @ExceptionHandler(MechanicNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMechanicNotFoundException(MechanicNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Erro interno do servidor: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}