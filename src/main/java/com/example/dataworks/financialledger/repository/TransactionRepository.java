package com.example.dataworks.financialledger.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dataworks.financialledger.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getByDate(LocalDate date);

    List<Transaction> getByType(String type);
    
    List<Transaction> findByUserId(Long user_id);
    
}
