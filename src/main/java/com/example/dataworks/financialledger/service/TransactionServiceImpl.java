package com.example.dataworks.financialledger.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.Exception.TransactionExceptionNotFound;
import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
import com.example.dataworks.financialledger.entity.Transaction;
import com.example.dataworks.financialledger.entity.TransactionType;
import com.example.dataworks.financialledger.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Transaction transaction2 = transactionRepository.save(transaction);
        if (transaction2 == null) {
            throw new TransactionExceptionNotFound("Transaction is not saved");
        }
        return transaction2;
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionExceptionNotFound("Transaction was not found"));
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transaction = transactionRepository.findAll();
        if (transaction == null) {
            throw new TransactionExceptionNotFound("Transaction is not found");
        }
        return transaction;
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionExceptionNotFound("Transaction is not found for r" + transactionId));

    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        List<Transaction> transactions = transactionRepository.findByDate(date);
        if (transactions == null) {
            throw new TransactionExceptionNotFound("Transactions are not found in this date");
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByType(TransactionType type) {
        List<Transaction> transaction = transactionRepository.findByType(type);
        if (transaction == null) {
            throw new TransactionExceptionNotFound("Transactions are not found for this type");
        }
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long userId) {

        List<Transaction> transactions = transactionRepository.findByUserUserId(userId);
        if (transactions == null) {
            throw new TransactionExceptionNotFound("Transactions are not found for this userId");
        }
        return transactions;
    }

    @Override
    public Transaction updateTransaction(Long transactionId, Transaction transaction) {
        Optional<Transaction> existingtransaction = transactionRepository.findById(transactionId);
        if (existingtransaction == null) {
            throw new UserExceptionNotFound("The Transactions is not found");
        }
        return transactionRepository.save(transaction);

    }

}
