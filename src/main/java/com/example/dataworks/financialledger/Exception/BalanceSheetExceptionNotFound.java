package com.example.dataworks.financialledger.Exception;

public class BalanceSheetExceptionNotFound extends RuntimeException{

  public BalanceSheetExceptionNotFound(String message){
    super(message);
  }
  public BalanceSheetExceptionNotFound(String message,Throwable cause){
    super(message,cause);
  }

}
