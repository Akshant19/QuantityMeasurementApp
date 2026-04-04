package org.apps.quantitymeasurement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerUC15 {

    @ExceptionHandler(QuantityMeasurementExceptionUC15.class)
    public ResponseEntity<String> handleDomainException(QuantityMeasurementExceptionUC15 e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception e) {
        return ResponseEntity.internalServerError().body("Internal server error");
    }
}