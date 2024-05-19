package com.example.dataworks.financialledger.service;

import java.util.List;

import com.example.dataworks.financialledger.entity.FinancialReport;

public interface FinancialReportService {

    public FinancialReport createFinancialReport(FinancialReport financialReport);

    public FinancialReport getFinancialReport(Long id);

    public void deleteFinancialReport(Long id);
    
    public void deleteFinancialReportByUserId(Long user_id);

    public List<FinancialReport> getFinancialReportByUserId(Long user_id);

    public List<FinancialReport> generateMonthlyReport(Long user_id, int year, int month);

    public List<FinancialReport> generateYearlyReport(Long user_id, int year);

    public List<FinancialReport> generateQuarterlyReport(Long user_id, int year, int quarter);
}
