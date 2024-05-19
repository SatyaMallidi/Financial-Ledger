package com.example.dataworks.financialledger.Exception;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value ={UserExceptionNotFound.class} )
    public ResponseEntity<Object> handlingUserExceptionNotFound (UserExceptionNotFound exceptionNotFound){

        ExceptionFound exceptionFound = new ExceptionFound(
            exceptionNotFound.getMessage(),LocalDateTime.now(),
            exceptionNotFound.getCause(), HttpStatus.NOT_FOUND);  
            
            return new ResponseEntity<>(exceptionFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value ={TransactionExceptionNotFound.class} )
    public ResponseEntity<Object> handlingTransactionExceptionNotFound(TransactionExceptionNotFound transactionExceptionNotFound){
          
          ExceptionFound exceptionFound = new ExceptionFound(
            transactionExceptionNotFound.getMessage(),LocalDateTime.now(),
            transactionExceptionNotFound.getCause(),HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(exceptionFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value ={BalanceSheetExceptionNotFound.class} )
    public ResponseEntity<Object> handlingBalanceSheetExceptionNotFound (BalanceSheetExceptionNotFound exceptionNotFound){

        ExceptionFound exceptionFound = new ExceptionFound(
            exceptionNotFound.getMessage(),LocalDateTime.now(),
            exceptionNotFound.getCause(), HttpStatus.NOT_FOUND);  
            
            return new ResponseEntity<>(exceptionFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value ={FinancialReportExceptionNotFound.class} )
    public ResponseEntity<Object> handlingFinancialExceptionNotFound (BalanceSheetExceptionNotFound exceptionNotFound){

        ExceptionFound exceptionFound = new ExceptionFound(
            exceptionNotFound.getMessage(),LocalDateTime.now(),
            exceptionNotFound.getCause(), HttpStatus.NOT_FOUND);  
            
            return new ResponseEntity<>(exceptionFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ExceptionFound> handleSQLException(SQLException ex) {
        ExceptionFound exceptionFound = new ExceptionFound(
            "Database error: " + ex.getMessage(),
            LocalDateTime.now(),
            ex.getCause(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(exceptionFound, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionFound> handleGenericException(Exception ex) {
        ExceptionFound exceptionFound = new ExceptionFound(
            "An error occurred: " + ex.getMessage(),
            LocalDateTime.now(),
            ex.getCause(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(exceptionFound, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
