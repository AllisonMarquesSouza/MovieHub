package com.br.moviehub.exception;

import com.br.moviehub.exception.personalizedExceptions.BadRequestException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class HandlerException {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleEntityNotFoundException(EntityNotFoundException entityNotFound) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message(entityNotFound.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("NotFoundException , Check the Documentation")
                        .details(entityNotFound.getCause() != null ? entityNotFound.getCause().toString() : "No details available")
                        .developerMessage(entityNotFound.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ExceptionDetails> handleEntityExistsException(EntityExistsException entityExistsException) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message(entityExistsException.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .title("EntityExistsException, Check the Documentation")
                        .details(entityExistsException.getCause() != null ? entityExistsException.getCause().toString() : "No details available")
                        .developerMessage(entityExistsException.getClass().getName())
                        .build(), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArg) {
        List<String> errorMessages = methodArg.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message("Validation failed for one or more fields.")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("MethodArgumentNotValidException, Check the Documentation")
                        .details(errorMessages.toString())
                        .developerMessage(methodArg.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionDetails> handleMethodArgumentNotValidException(MissingServletRequestParameterException missingParam) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message("Missing parameter '" + missingParam.getParameterName() + "'"+" check it")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("MissingServletRequestParameterException, Check the Documentation")
                        .details(missingParam.getCause() != null ? missingParam.getCause().toString() : "No details available")
                        .developerMessage(missingParam.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException badRequest) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .message(badRequest.getMessage())
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("BadRequestException, Check the Documentation")
                        .details(badRequest.getCause() != null ? badRequest.getCause().toString() : "No details available")
                        .developerMessage(badRequest.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}