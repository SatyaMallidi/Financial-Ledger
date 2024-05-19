package com.example.dataworks.financialledger.Exception;

public class UserExceptionNotFound extends RuntimeException {

    
    public UserExceptionNotFound(String message){
        super(message);
    }
    
    public UserExceptionNotFound(String message, Throwable cause) {
        super(message, cause);
    }
    
}
