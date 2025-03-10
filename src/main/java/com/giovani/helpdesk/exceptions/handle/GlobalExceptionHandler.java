package com.giovani.helpdesk.exceptions.handle;

import com.giovani.helpdesk.exceptions.DataIntegrityViolationException;
import com.giovani.helpdesk.exceptions.ObjectNotFoundException;
import com.giovani.helpdesk.exceptions.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseException> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ValidationError errors = new ValidationError(
                currentTimeMillis(),
                BAD_REQUEST.value(),
                "Validation Error",
                "Erro na validação dos atributos:",
                request.getRequestURI()
        );
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            errors.addErrors(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity.status(BAD_REQUEST).body(errors);
    }
}
