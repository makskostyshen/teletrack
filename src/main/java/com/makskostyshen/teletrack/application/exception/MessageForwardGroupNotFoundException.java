package com.makskostyshen.teletrack.application.exception;

import lombok.Getter;

@Getter
public class MessageForwardGroupNotFoundException extends RuntimeException {
    private final String message;
    public MessageForwardGroupNotFoundException(final String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
}
