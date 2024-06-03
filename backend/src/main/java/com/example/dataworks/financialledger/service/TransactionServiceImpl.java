package com.example.dataworks.financialledger.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dataworks.financialledger.DTO.TransactionDTO;
import com.example.dataworks.financialledger.Exception.TransactionExceptionNotFound;
import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
import com.example.dataworks.financialledger.entity.Transaction;
import com.example.dataworks.financialledger.entity.TransactionType;
import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.repository.TransactionRepository;
import com.example.dataworks.financialledger.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Transaction saveTransaction(Transaction transaction) {
        Transaction transaction2 = transactionRepository.save(transaction);
        if (transaction2 == null) {
            throw new TransactionExceptionNotFound("Transaction is not saved");
        }
        return transaction2;
    }

    @Override
    @Transactional
    public void deleteTransaction(Long transactionId) {
        transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionExceptionNotFound("Transaction was not found"));
        transactionRepository.deleteById(transactionId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions == null) {
            throw new TransactionExceptionNotFound("Transactions are not found");
        }
        return transactions;
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionExceptionNotFound("Transaction is not found for id " + transactionId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        List<Transaction> transactions = transactionRepository.findByDate(date);
        if (transactions == null) {
            throw new TransactionExceptionNotFound("Transactions are not found for this date");
        }
        return transactions;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByType(TransactionType type) {
        List<Transaction> transactions = transactionRepository.findByType(type);
        if (transactions == null) {
            throw new TransactionExceptionNotFound("Transactions are not found for this type");
        }
        return transactions;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByUserId(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserUserId(userId);
        if (transactions == null) {
            throw new TransactionExceptionNotFound("Transactions are not found for this userId");
        }
        return transactions;
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Long transactionId, Transaction transaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
        if (!existingTransaction.isPresent()) {
            throw new UserExceptionNotFound("The Transaction is not found");
        }
        return transactionRepository.save(transaction);
    }

   @Override
public Transaction createTransaction(TransactionDTO transactionDTO) {
    User user = userRepository.findById(transactionDTO.getUserId())
                              .orElseThrow(() -> new RuntimeException("User not found"));
    Transaction transaction = new Transaction();
    transaction.setUser(user);
    transaction.setDate(transactionDTO.getDate() != null ? transactionDTO.getDate() : LocalDate.now());
    transaction.setAmount(transactionDTO.getAmount());
    transaction.setDescription(transactionDTO.getDescription());
    transaction.setType(transactionDTO.getType());
    return transactionRepository.save(transaction);
}
}
