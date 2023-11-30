package com.makskostyshen.teletrack.application.exception;

import lombok.Getter;

@Getter
public class MessageForwardGroupParsingException extends RuntimeException {
    private final String message;
    public MessageForwardGroupParsingException(final String errorMessage, final Exception e) {
        super(errorMessage, e);
        this.message = errorMessage;
    }
    public MessageForwardGroupParsingException(final String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
}
