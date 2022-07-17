package org.example.exception;

public class PlaceholderNotFoundException extends RuntimeException {

    public PlaceholderNotFoundException(String message) {
        super(message);
    }
}
