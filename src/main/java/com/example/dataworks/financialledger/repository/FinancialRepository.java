package com.example.dataworks.financialledger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.dataworks.financialledger.entity.FinancialReport;

@Repository
public interface FinancialRepository extends JpaRepository<FinancialReport, Long> {

    @Query("SELECT fr FROM FinancialReport fr WHERE fr.user.id = :userId AND YEAR(fr.periodStart) = :year AND MONTH(fr.periodStart) = :month")
    List<FinancialReport> findMonthlyReports(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT fr FROM FinancialReport fr WHERE fr.user.id = :userId AND YEAR(fr.periodStart) = :year")
    List<FinancialReport> findYearlyReports(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT fr FROM FinancialReport fr WHERE fr.user.id = :userId AND YEAR(fr.periodStart) = :year AND QUARTER(fr.periodStart) = :quarter")
    List<FinancialReport> findQuarterlyReports(@Param("userId") Long userId, @Param("year") int year, @Param("quarter") int quarter);

    List<FinancialReport> findByUserId(Long userId);
}
