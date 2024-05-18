package com.example.dataworks.financialledger.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.entity.Transaction;
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
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> getAll() {
        List<Transaction> transaction = transactionService.getAllTransactions();
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Long Transaction_id) {
        Transaction transaction = transactionService.getTransactionById(Transaction_id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Transaction>> getByUserId(@PathVariable Long user_id) {
        List<Transaction> transaction = transactionService.getTransactionsByUserId(user_id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/type")
    public ResponseEntity<List<Transaction>> getByType(@RequestParam String type) {
        List<Transaction> transaction = transactionService.getTransactionsByType(type);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date")
    public ResponseEntity<List<Transaction>> getByDate(@RequestParam @DateTimeFormat LocalDate date) {
        List<Transaction> transaction = transactionService.getTransactionsByDate(date);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/")
    public ResponseEntity<Transaction> newTransaction(@RequestBody Transaction transaction){
        transactionService.createTransaction(transaction);
         if (transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteById(@PathVariable Long Transaction_id) {
        Transaction transaction = transactionService.getTransactionById(Transaction_id);
        if (transaction != null) {
            transactionService.deleteTransaction(Transaction_id);
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long Transaction_id,@RequestBody Transaction trans){
        Transaction transaction = transactionService.getTransactionById(Transaction_id);
        if (transaction != null) {
            transactionService.updateTransaction(trans);
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.notFound().build();
    }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Transaction> partiallyUpdateTransaction(@PathVariable Long Transaction_id, @RequestBody Transaction Trans) {
        Transaction transaction = transactionService.getTransactionById(Transaction_id);
        if (transaction !=null) {
            if (Trans.getDate() != null) {
                transaction.setDate(Trans.getDate());
            }
            if (Trans.getAmount() != null) {
                transaction.setAmount(Trans.getAmount());
            }
            if (Trans.getDescription() != null) {
                transaction.setDescription(Trans.getDescription());
            }
            if (Trans.getUser() != null) {
                transaction.setUser(Trans.getUser());
            }
            if (Trans.getType() != null) {
                transaction.setType(Trans.getType());
            }
            
            Transaction newTransaction = transactionService.updateTransaction(transaction);
            return ResponseEntity.ok(newTransaction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
    
