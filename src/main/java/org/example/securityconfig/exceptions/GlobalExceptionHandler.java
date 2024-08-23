package org.example.securityconfig.exceptions;

import org.eclipse.angus.mail.iap.BadCommandException;
import org.example.securityconfig.dto.ExceptionDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity<?> validationExceptionHandler(MethodArgumentNotValidException e){
        Map<String, String > errors=new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        errors.put("status","400");
        return ResponseEntity.status(400).body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public HttpEntity<?> badCredentials(Exception e){
        return ResponseEntity.status(401).body(ExceptionDto.builder().status(401).message(e.getMessage()));
    }
}
