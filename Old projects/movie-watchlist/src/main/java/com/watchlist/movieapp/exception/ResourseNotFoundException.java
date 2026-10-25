package com.watchlist.movieapp.exception;

public class ResourseNotFoundException extends RuntimeException {
    private String resourceName; // could be any resource name, e.g., "Movie", "User", etc.
    private String fieldName; //could be any field name, e.g., "id", "email", etc.
    private Object fieldValue; //could be any type of value


    public ResourseNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourseNotFoundException(String message) {
        super(message);
    }
}
