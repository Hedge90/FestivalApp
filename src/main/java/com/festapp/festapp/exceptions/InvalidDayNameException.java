package com.festapp.festapp.exceptions;

public class InvalidDayNameException extends RuntimeException {

    public InvalidDayNameException() {
        super("The day name provided is not valid");
    }
}
