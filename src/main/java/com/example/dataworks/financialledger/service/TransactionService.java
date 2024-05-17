package com.example.dataworks.financialledger.service;

import java.time.LocalDate;
import java.util.List;

import com.example.dataworks.financialledger.entity.Transaction;

public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);
    public Transaction updateTransaction(Transaction transaction);  
    public void deleteTransaction(Long Transaction_id);  
    public Transaction getTransactionById(Long Transaction_id);
    public List<Transaction> getAllTransactions();
    public List<Transaction> getTransactionsByDate(LocalDate date);
    public List<Transaction> getTransactionsByType(String type);
    public List<Transaction> getTransactionsByUserId(Long user_id);
    
}
