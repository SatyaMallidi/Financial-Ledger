package com.example.dataworks.financialledger.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.dataworks.financialledger.entity.FinancialReport;
import com.example.dataworks.financialledger.repository.FinancialRepository;

import jakarta.transaction.Transactional;

public class FinancialReportImplService implements FinancialReportService {
     
    @Autowired
    private FinancialRepository financialRepository;

    @Override
    @Transactional
    public FinancialReport createFinancialReport(FinancialReport financialReport) {
        return financialRepository.save(financialReport);
    }

    @Override
    @Transactional
    public FinancialReport getFinancialReport(Long id) {
       return financialRepository.findById(id).get();
    }

    

    @Override
    @Transactional
    public void deleteFinancialReport(Long id) {
        financialRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<FinancialReport> getFinancialReportByUserId(Long user_Id) {
        return financialRepository.findByUserId(user_Id);
    }

    @Override
    @Transactional
    public List<FinancialReport> generateMonthlyReport(Long userId, int year, int month) {
        return financialRepository.findMonthlyReports(userId, year, month);
    }

    @Override
    @Transactional
    public List<FinancialReport> generateYearlyReport(Long userId, int year) {
        return financialRepository.findYearlyReports(userId, year);
    }

    @Override
    @Transactional
    public List<FinancialReport> generateQuarterlyReport(Long userId, int year, int quarter) {
        return financialRepository.findQuarterlyReports(userId, year, quarter);
    }

    @Override
    public void deleteFinancialReportByUserId(Long user_id) {
         financialRepository.deleteAll();
    }
    
}
