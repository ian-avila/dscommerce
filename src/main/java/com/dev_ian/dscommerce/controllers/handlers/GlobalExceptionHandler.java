package com.dev_ian.dscommerce.controllers.handlers;

import com.dev_ian.dscommerce.dto.ErrorResponse;
import com.dev_ian.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> databaseIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {

        String customMessage = "Data Integrity Violation";

        ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.CONFLICT.value(), customMessage, request.getRequestURI());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Invalid argument", request.getRequestURI());
        e.getFieldErrors().forEach(errorResponse::addFieldError);

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {

        String customMessage = "Invalid request body";

        ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.BAD_REQUEST.value(), customMessage, request.getRequestURI());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

}
