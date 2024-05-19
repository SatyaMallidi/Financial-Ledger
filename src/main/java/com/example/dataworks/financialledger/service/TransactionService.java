package com.example.dataworks.financialledger.service;

import java.time.LocalDate;
import java.util.List;

import com.example.dataworks.financialledger.entity.Transaction;
import com.example.dataworks.financialledger.entity.TransactionType;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);

    void deleteTransaction(Long transactionId);

    Transaction getTransactionById(Long transactionId);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByDate(LocalDate date);

    List<Transaction> getTransactionsByType(TransactionType type);

    List<Transaction> getTransactionsByUserId(Long userId);

}
