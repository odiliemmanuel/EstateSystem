package com.realestate.exceptions;

public class ResidentCodeDoesNotExitException extends RuntimeException {
    public ResidentCodeDoesNotExitException(String message) {
        super(message);
    }
}
