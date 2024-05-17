package com.example.dataworks.financialledger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataworks.financialledger.entity.BalanceSheet;

public interface BalanceSheetRepository extends JpaRepository<BalanceSheet,Long>{

    List<BalanceSheet> findByUserId(Long user_id);
    
}
