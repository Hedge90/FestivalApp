package com.festapp.festapp.exceptions;

public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException() {
        super("No artist found with this name");
    }
}
