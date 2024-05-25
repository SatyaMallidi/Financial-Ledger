package com.example.dataworks.financialledger.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dataworks.financialledger.entity.Transaction;
import com.example.dataworks.financialledger.entity.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDate(LocalDate date);

    List<Transaction> findByType(TransactionType type);

    List<Transaction> findByUserUserId(Long userId);

}
