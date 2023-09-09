package com.festapp.festapp.exceptions;

import com.festapp.festapp.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {
    @ExceptionHandler(value = ArtistNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(ArtistNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
