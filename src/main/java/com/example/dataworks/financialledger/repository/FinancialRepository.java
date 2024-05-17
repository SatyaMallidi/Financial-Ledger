package com.example.dataworks.financialledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataworks.financialledger.entity.FinancialReport;
public interface FinancialRepository extends JpaRepository<FinancialReport, Long> {
    
}
