package com.festapp.festapp.exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
        super("Email or Password is invalid");
    }
}
