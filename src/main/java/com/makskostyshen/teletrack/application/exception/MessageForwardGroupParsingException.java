package com.makskostyshen.teletrack.application.exception;

public class MessageForwardGroupParsingException extends RuntimeException {
    public MessageForwardGroupParsingException(final String message, final Exception e) {
        super(message, e);
    }
    public MessageForwardGroupParsingException(final String message) {
        super(message);
    }
}
