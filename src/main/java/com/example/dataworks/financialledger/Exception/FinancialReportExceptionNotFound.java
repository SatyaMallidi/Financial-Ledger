package com.example.dataworks.financialledger.Exception;

public class FinancialReportExceptionNotFound  extends RuntimeException{

  public FinancialReportExceptionNotFound(String message){
    super(message);
  }
  public FinancialReportExceptionNotFound(String message,Throwable cause){
    super(message,cause);
  }

}
