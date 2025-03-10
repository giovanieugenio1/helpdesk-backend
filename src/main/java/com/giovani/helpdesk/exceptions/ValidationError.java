package com.giovani.helpdesk.exceptions;

import com.giovani.helpdesk.exceptions.handle.ResponseException;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ResponseException {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
super();    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
