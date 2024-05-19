package com.example.dataworks.financialledger.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.Exception.FinancialReportExceptionNotFound;
import com.example.dataworks.financialledger.entity.FinancialReport;
import com.example.dataworks.financialledger.repository.FinancialRepository;

import jakarta.transaction.Transactional;

@Service
public class FinancialReportImplService implements FinancialReportService {

    @Autowired
    private FinancialRepository financialRepository;

    @Override
    @Transactional
    public FinancialReport createFinancialReport(FinancialReport financialReport) {
        FinancialReport newFinancialReport = financialRepository.save(financialReport);
        if (newFinancialReport == null) {
            throw new FinancialReportExceptionNotFound("FinancialReport is not saved");
        }
        return newFinancialReport;
    }

    @Override
    @Transactional
    public FinancialReport getFinancialReport(Long id) {
        return financialRepository.findById(id)
                .orElseThrow(() -> new FinancialReportExceptionNotFound("FinancialReport is not found"));
    }

    @Override
    @Transactional
    public void deleteFinancialReport(Long id) {
        financialRepository.findById(id)
                .orElseThrow(() -> new FinancialReportExceptionNotFound("FinancialReport is not deleted"));
        financialRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<FinancialReport> getFinancialReportByUserId(Long user_id) {
        List<FinancialReport> financialReports = financialRepository.findByUserId(user_id);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReport is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional
    public List<FinancialReport> generateMonthlyReport(Long user_id, int year, int month) {
        List<FinancialReport> financialReports = financialRepository.findMonthlyReports(user_id, year, month);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional
    public List<FinancialReport> generateYearlyReport(Long user_id, int year) {
        List<FinancialReport> financialReports = financialRepository.findYearlyReports(user_id, year);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional
    public List<FinancialReport> generateQuarterlyReport(Long user_id, int year, int quarter) {
        List<FinancialReport> financialReports = financialRepository.findQuarterlyReports(user_id, year, quarter);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports is not found");
        }
        return financialReports;
    }

    @Override
public void deleteFinancialReportByUserId(Long user_id) {
    List<FinancialReport> reports = financialRepository.findByUserId(user_id);
    if (reports.isEmpty()) {
        throw new FinancialReportExceptionNotFound("Financial reports not found for user with id: " + user_id);
    }
    financialRepository.deleteAll(reports);
}
}

