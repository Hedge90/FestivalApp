package com.festapp.festapp.exceptions;

public class ArtistAlreadyExistsException extends RuntimeException {

    public ArtistAlreadyExistsException() {
        super("The artist you are trying to create already exists in the database");
    }
}
