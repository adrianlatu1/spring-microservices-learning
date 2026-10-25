package com.watchlist.movieapp.exception;


import java.util.HashMap;
import java.util.Map;

//400 Bad Request - Validation errors, such as missing required fields or invalid data formats.
public class ValidationException extends RuntimeException {

    private Map<String, String> errors;

    public ValidationException(String message) {
        super(message);
        this.errors = new HashMap<>();
    }

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public ValidationException(String field, String message){
        super("Validation failed for field: " + field);
        this.errors = new HashMap<>();
        this.errors.put(field, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
