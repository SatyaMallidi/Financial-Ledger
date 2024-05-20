package com.example.dataworks.financialledger.service;

import java.util.List;

import com.example.dataworks.financialledger.entity.FinancialReport;

public interface FinancialReportService {

    FinancialReport createFinancialReport(FinancialReport financialReport);

    FinancialReport updFinancialReport(Long id, FinancialReport financialReport);

    FinancialReport getFinancialReport(Long id);

    void deleteFinancialReport(Long id);

    void deleteFinancialReportByUserId(Long userId);

    List<FinancialReport> getFinancialReportByUserId(Long userId);

    List<FinancialReport> generateMonthlyReport(Long userId, int year, int month);

    List<FinancialReport> generateYearlyReport(Long userId, int year);

    List<FinancialReport> generateQuarterlyReport(Long userId, int year, int quarter);

}
