package com.spabooking.exception;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.LockTimeoutException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
   
    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<?> handleException(AppException e){
        return ResponseEntity.status(400)
                .body(new ExceptionResponse(400,e.getMessage()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ResponseEntity<?> handleExceptionLogin(BadCredentialsException e){
        return ResponseEntity.status(403)
                .body(new ExceptionResponse(403,"Email or password incorrect"));
    }
    @ExceptionHandler(AuthorizationServiceException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ResponseEntity<?> handleExceptionAuthor(AuthorizationServiceException e){
        return ResponseEntity.status(403)
                .body(new ExceptionResponse(403,e.getMessage()));
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<?> emptyResult(AuthorizationServiceException e){
        return ResponseEntity.status(404)
                .body(new ExceptionResponse(404,e.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<?> notFound(NotFoundException e){
        return ResponseEntity.status(404)
                .body(new ExceptionResponse(404,e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<?> validException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(400,errors));
    }

    @ExceptionHandler(LockTimeoutException.class)
    private ResponseEntity<?> timeOutQuery(LockTimeoutException e){
        return ResponseEntity.status(500).body(new ExceptionResponse(500,"Payment error"));
    }
}
