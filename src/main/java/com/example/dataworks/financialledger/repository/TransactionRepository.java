package com.example.dataworks.financialledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataworks.financialledger.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
