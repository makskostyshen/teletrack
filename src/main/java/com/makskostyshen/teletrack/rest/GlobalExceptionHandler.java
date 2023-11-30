package com.makskostyshen.teletrack.rest;

import com.makskostyshen.teletrack.application.exception.MessageForwardGroupAlreadyExistsException;
import com.makskostyshen.teletrack.application.exception.MessageForwardGroupNotFoundException;
import com.makskostyshen.teletrack.application.exception.MessageForwardGroupParsingException;
import com.makskostyshen.teletrack.rest.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MessageForwardGroupAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleForwardGroupAlreadyExistsException(
            final MessageForwardGroupAlreadyExistsException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(MessageForwardGroupNotFoundException.class)
    public ResponseEntity<ErrorDto> handleForwardGroupNotFoundException(final MessageForwardGroupNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(MessageForwardGroupParsingException.class)
    public ResponseEntity<ErrorDto> handleForwardGroupParsingException(final MessageForwardGroupParsingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorDto(e.getMessage()));
    }
}
