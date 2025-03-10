package com.giovani.helpdesk.exceptions.handle;

import com.giovani.helpdesk.exceptions.DataIntegrityViolationException;
import com.giovani.helpdesk.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResponseException> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
        ResponseException error = new ResponseException(
                currentTimeMillis(),
                NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseException> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        ResponseException error = new ResponseException(
                currentTimeMillis(),
                BAD_REQUEST.value(),
                "Violação de dados",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(BAD_REQUEST).body(error);
    }
}
