package com.hayelny.core;

import jakarta.persistence.EntityNotFoundException;
import java.nio.file.NoSuchFileException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class, NoSuchFileException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        Message error = new Message("No entity with such id");
        return handleExceptionInternal(ex, error.content(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}

