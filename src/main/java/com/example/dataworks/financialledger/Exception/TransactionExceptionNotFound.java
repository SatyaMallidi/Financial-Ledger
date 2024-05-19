package com.example.dataworks.financialledger.Exception;

public class TransactionExceptionNotFound extends RuntimeException {

    
  public TransactionExceptionNotFound(String message){
      super(message);
  }
  
  public TransactionExceptionNotFound(String message, Throwable cause) {
      super(message, cause);
  }
  
}
