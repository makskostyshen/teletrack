package com.makskostyshen.teletrack.application.exception;

import lombok.Getter;

@Getter
public class IllegalAuthenticationActionException extends RuntimeException {
    private String message;
    public IllegalAuthenticationActionException(final String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
}
