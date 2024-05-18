package com.example.dataworks.financialledger.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> handleException(UserException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                                     exception.getMessage(),
                                                     webRequest.getDescription(false), 
                                                     "USER_NOT_FOUND");
        
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGeneralException(Exception exception, WebRequest webRequest ) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                                    exception.getMessage(),
                                                    webRequest.getDescription(false), 
                                                     "INTERNAL_SERVER_ERROR");
    
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);   
    }
    
}
