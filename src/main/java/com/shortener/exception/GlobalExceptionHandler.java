package com.shortener.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(a->{
            String fieldName = ((FieldError)a).getField();
            String errorMEssage=a.getDefaultMessage();
            errors.put(fieldName,errorMEssage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error",ex.getMessage());
        return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExist.class)
    public ResponseEntity<?> resourceAlreadyExist(ResourceAlreadyExist ex){
        Map<String,String> errors = new HashMap<>();
        errors.put("error",ex.getMessage());
        return new ResponseEntity<>(errors,HttpStatus.CONFLICT);
    }
}
