package com.example.dataworks.financialledger.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.entity.Transaction;
import com.example.dataworks.financialledger.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;  

    @Override
    public Transaction createTransaction(Transaction transaction) {
            return transactionRepository.save(transaction);
            
    }

    @Override
    public void deleteTransaction(Long Transaction_id) {
        transactionRepository.deleteById(Transaction_id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    };
    

    @Override
    public Transaction getTransactionById(Long Transaction_id) {
        return transactionRepository.findById(Transaction_id).get();
    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.getByDate(date);
    }

    @Override
    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.getByType(type);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long user_id) {
        return transactionRepository.findByUserId(user_id);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
}
