package com.norbert.tfa.exception;

import dev.samstevens.totp.exceptions.QrGenerationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {TFAException.class, AccessException.class, UsernameNotFoundException.class, BadRegistrationRequest.class, BadCredentialsException.class, DisabledException.class })
    public ResponseEntity<Object> handleBadRequest(RuntimeException exception, HttpServletRequest request){
        ApiException apiException = ApiException.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .timestamp(ZonedDateTime.now())
                .message(exception.getMessage())
                .path(request.getPathInfo())
                .build();
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
