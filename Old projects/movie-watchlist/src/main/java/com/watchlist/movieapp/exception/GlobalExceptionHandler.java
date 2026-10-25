package com.watchlist.movieapp.exception;


import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice //indicates that this class will handle exceptions globally across the application and will return JSON responses
public class GlobalExceptionHandler extends RuntimeException {

    //these methods are called automatically when the specified exceptions are thrown in any part of the application. They create an ErrorResponse object containing details about the error and return it with the appropriate HTTP status code.

    //404 Resource not found
    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourseNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    //400 Bad request
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ValidationException ex) {
       /* ErrorResponse errorResponse = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(400).body(errorResponse);*/
        //ResponseEntity is a read only interface
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),"Bad request", ex.getMessage(),ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDTO);
    }

/*    @ExceptionHandler(Exception.class)  // custom exception handler
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
    }*/

     //500 Internal server error
}
