package com.festapp.festapp.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(){
        super("Email already Exists");
    }
}
