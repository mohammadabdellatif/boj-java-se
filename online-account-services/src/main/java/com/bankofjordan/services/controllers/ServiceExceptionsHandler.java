package com.bankofjordan.services.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionsHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        ServiceError serviceError = new ServiceError("01", ex.getMessage());
        ResponseEntity<ServiceError> error = ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceError);
        return error;
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException ex) {
        ServiceError serviceError = new ServiceError("02", ex.getMessage());
        ResponseEntity<ServiceError> error = ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceError);
        return error;
    }
}
