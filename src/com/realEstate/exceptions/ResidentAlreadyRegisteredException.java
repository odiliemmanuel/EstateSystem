package com.realEstate.exceptions;

public class ResidentAlreadyRegisteredException extends RuntimeException {
    public ResidentAlreadyRegisteredException(String message) {
        super(message);
    }
}
