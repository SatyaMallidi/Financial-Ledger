package com.example.dataworks.financialledger.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ExceptionFound(String message,LocalDateTime timeStamp,Throwable throwable,HttpStatus httpStatus) {
    
}
