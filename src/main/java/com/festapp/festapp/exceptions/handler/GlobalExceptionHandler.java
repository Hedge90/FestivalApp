package com.festapp.festapp.exceptions.handler;

import com.festapp.festapp.dtos.ErrorDTO;
import com.festapp.festapp.exceptions.ArtistAlreadyExistsException;
import com.festapp.festapp.exceptions.ArtistNotFoundException;
import com.festapp.festapp.exceptions.InvalidDayNameException;
import com.festapp.festapp.exceptions.NoSuchDayExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@RestControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {
    @ExceptionHandler(value = ArtistNotFoundException.class)
    public ResponseEntity<Object> handleArtistNotFoundException(ArtistNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidDayNameException.class)
    public ResponseEntity<Object> handleInvalidDayNameException(InvalidDayNameException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ArtistAlreadyExistsException.class)
    public ResponseEntity<Object> handleArtistAlreadyExistsException(ArtistAlreadyExistsException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchDayExistsException.class)
    public ResponseEntity<Object> handleNoSuchDayExistsException(NoSuchDayExistsException exception) {
        return new ResponseEntity<>(new ErrorDTO("error", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
