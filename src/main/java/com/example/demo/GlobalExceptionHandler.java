package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.PostNotCreatedException;
import com.example.demo.exception.PostNotGetException;
import com.example.demo.model.ErrorResponseBody;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotCreatedException.class)
    public ResponseEntity<ErrorResponseBody> handlePostNotCreatedException(PostNotCreatedException e) {
        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        errorResponseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponseBody.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseBody);
    }

    @ExceptionHandler(PostNotGetException.class)
        public ResponseEntity<ErrorResponseBody> handlePostNotGetException(PostNotCreatedException e) {
        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        errorResponseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponseBody.setErrorMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseBody);
    }

}
