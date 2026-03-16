package com.realEstate.exceptions;

public class ResidentDoesNotExistException extends RuntimeException {
    public ResidentDoesNotExistException(String message) {
        super(message);
    }
}
