package com.example.dataworks.financialledger.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.entity.Transaction;
import com.example.dataworks.financialledger.entity.TransactionType;
import com.example.dataworks.financialledger.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/public/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public List<Transaction> getAll() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return transactions;
    }

    @GetMapping("/{transactionId}")
    public Transaction getById(@PathVariable Long transactionId) {
        Transaction transaction = transactionService.getTransactionById(transactionId);
        return transaction;
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getByUserId(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        return transactions;
    }

    @GetMapping("/type")
    public List<Transaction> getByType(@RequestParam TransactionType type) {
        List<Transaction> transactions = transactionService.getTransactionsByType(type);
        return transactions;
    }

    @GetMapping("/date")
    public List<Transaction> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Transaction> transactions = transactionService.getTransactionsByDate(date);
        return transactions;
    }

    @PostMapping("/")
    public Transaction newTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return createdTransaction;
    }

    @DeleteMapping("/{transactionId}")
    public void deleteById(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }

    @PutMapping("/{transactionId}")
    public Transaction updateTransaction(@PathVariable Long transactionId, @RequestBody Transaction transaction) {
        Transaction updatedTransaction = transactionService.updateTransaction(transactionId, transaction);
        return updatedTransaction;
    }

    @PatchMapping("/{transactionId}")
    public Transaction partiallyUpdateTransaction(@PathVariable Long transactionId,
            @RequestBody Transaction transaction) {
        Transaction existingTransaction = transactionService.getTransactionById(transactionId);
        if (transaction.getDate() != null) {
            existingTransaction.setDate(transaction.getDate());
        }
        if (transaction.getAmount() != null) {
            existingTransaction.setAmount(transaction.getAmount());
        }
        if (transaction.getDescription() != null) {
            existingTransaction.setDescription(transaction.getDescription());
        }
        if (transaction.getUser() != null) {
            existingTransaction.setUser(transaction.getUser());
        }
        if (transaction.getType() != null) {
            existingTransaction.setType(transaction.getType());
        }
        return transactionService.updateTransaction(transactionId, existingTransaction);
    }

}
