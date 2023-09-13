package com.festapp.festapp.exceptions;

public class NoSuchDayExistsException extends RuntimeException {

    public NoSuchDayExistsException() {
        super("There is no such day in the database!");
    }
}