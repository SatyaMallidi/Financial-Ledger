package com.example.dataworks.financialledger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.dataworks.financialledger.entity.FinancialReport;

@Repository
public interface FinancialRepository extends JpaRepository<FinancialReport, Long> {

    @Query("SELECT fr FROM FinancialReport fr WHERE fr.user.user_id = :user_id AND FUNCTION('YEAR', fr.periodStart) = :year AND FUNCTION('MONTH', fr.periodStart) = :month")
    List<FinancialReport> findMonthlyReports(@Param("user_id") Long user_id, @Param("year") int year, @Param("month") int month);

    @Query("SELECT fr FROM FinancialReport fr WHERE fr.user.user_id = :user_id AND FUNCTION('YEAR', fr.periodStart) = :year")
    List<FinancialReport> findYearlyReports(@Param("user_id") Long user_id, @Param("year") int year);

    @Query("SELECT fr FROM FinancialReport fr WHERE fr.user.user_id = :user_id AND FUNCTION('YEAR', fr.periodStart) = :year AND FUNCTION('QUARTER', fr.periodStart) = :quarter")
    List<FinancialReport> findQuarterlyReports(@Param("user_id") Long user_id, @Param("year") int year, @Param("quarter") int quarter);

    List<FinancialReport> findByUserId(Long user_id);
}


    
