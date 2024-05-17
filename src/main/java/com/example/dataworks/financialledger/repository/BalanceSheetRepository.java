package com.example.dataworks.financialledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataworks.financialledger.entity.BalanceSheet;

public interface BalanceSheetRepository extends JpaRepository<BalanceSheet,Long>{
    
}
