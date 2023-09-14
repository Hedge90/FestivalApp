package com.festapp.festapp.exceptions;


public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email already Exists");
    }
}
