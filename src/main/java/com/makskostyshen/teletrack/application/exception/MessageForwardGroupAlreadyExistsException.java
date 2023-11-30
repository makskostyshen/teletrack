package com.makskostyshen.teletrack.application.exception;

import lombok.Getter;

@Getter
public class MessageForwardGroupAlreadyExistsException extends RuntimeException {
    private final String message;
    public MessageForwardGroupAlreadyExistsException(final String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
}
