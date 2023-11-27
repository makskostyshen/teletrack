package com.makskostyshen.teletrack.application.exception;

public class MessageTypeParsingException extends RuntimeException {
    public MessageTypeParsingException(final String message, final Exception e) {
        super(message, e);
    }
    public MessageTypeParsingException(final String message) {
        super(message);
    }
}
