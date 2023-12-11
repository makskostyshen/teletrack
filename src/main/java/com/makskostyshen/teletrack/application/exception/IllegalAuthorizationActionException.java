package com.makskostyshen.teletrack.application.exception;

import lombok.Getter;

@Getter
public class IllegalAuthorizationActionException extends RuntimeException {
    private final String message;
    public IllegalAuthorizationActionException(final String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
}
