package com.example.dataworks.financialledger.service;

import java.util.List;

import com.example.dataworks.financialledger.entity.FinancialReport;

public interface FinancialReportService {

    public FinancialReport createFinancialReport(FinancialReport financialReport);

    public FinancialReport getFinancialReport(Long id);

    public void deleteFinancialReport(Long id);
    
    public void deleteFinancialReportByUserId(Long user_id);

    public List<FinancialReport> getFinancialReportByUserId(Long userId);

    public List<FinancialReport> generateMonthlyReport(Long userId, int year, int month);

    public List<FinancialReport> generateYearlyReport(Long userId, int year);

    public List<FinancialReport> generateQuarterlyReport(Long userId, int year, int quarter);
}
