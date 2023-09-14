package com.festapp.festapp.exceptions;

public class DayAlreadyExistsException extends RuntimeException {
    public DayAlreadyExistsException() {
        super("Day already exists");
    }
}
