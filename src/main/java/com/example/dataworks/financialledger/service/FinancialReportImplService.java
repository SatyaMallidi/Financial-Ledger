package com.example.dataworks.financialledger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dataworks.financialledger.Exception.FinancialReportExceptionNotFound;
import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
import com.example.dataworks.financialledger.entity.FinancialReport;
import com.example.dataworks.financialledger.repository.FinancialRepository;

@Service
public class FinancialReportImplService implements FinancialReportService {

    @Autowired
    private FinancialRepository financialRepository;

    @Override
    @Transactional
    public FinancialReport createFinancialReport(FinancialReport financialReport) {
        FinancialReport newFinancialReport = financialRepository.save(financialReport);
        if (newFinancialReport == null) {
            throw new FinancialReportExceptionNotFound(
                    "FinancialReport is not saved");
        }
        return newFinancialReport;
    }

    @Override
    @Transactional
    public FinancialReport getFinancialReport(Long id) {
        return financialRepository.findById(id)
                .orElseThrow(() -> new FinancialReportExceptionNotFound(
                        "FinancialReport is not found"));
    }

    @Override
    @Transactional
    public void deleteFinancialReport(Long id) {
        financialRepository.findById(id)
                .orElseThrow(() -> new FinancialReportExceptionNotFound(
                        "FinancialReport is not deleted"));
        financialRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<FinancialReport> getFinancialReportByUserId(Long userId) {
        List<FinancialReport> financialReports = financialRepository.findByUserUserId(userId);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound(
                    "FinancialReport is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional
    public List<FinancialReport> generateMonthlyReport(Long userId, int year, int month) {
        List<FinancialReport> financialReports = financialRepository.findMonthlyReports(userId, year, month);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional
    public List<FinancialReport> generateYearlyReport(Long userId, int year) {
        List<FinancialReport> financialReports = financialRepository.findYearlyReports(userId, year);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional
    public List<FinancialReport> generateQuarterlyReport(Long userId, int year, int quarter) {
        List<FinancialReport> financialReports = financialRepository.findQuarterlyReports(userId, year, quarter);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound(
                    "FinancialReports is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional
    public void deleteFinancialReportByUserId(Long userId) {
        List<FinancialReport> reports = financialRepository.findByUserUserId(userId);
        if (reports.isEmpty()) {
            throw new FinancialReportExceptionNotFound("Financial reports not found for user with id: " + userId);
        }
        financialRepository.deleteAll(reports);
    }

    @Override
    public FinancialReport updFinancialReport(Long id, FinancialReport financialReport) {
        Optional<FinancialReport> existingfinancialreport = financialRepository.findById(id);
        if (existingfinancialreport == null) {
            throw new UserExceptionNotFound("The FinancialReport is not found");
        }
        return financialRepository.save(financialReport);

    }

}
