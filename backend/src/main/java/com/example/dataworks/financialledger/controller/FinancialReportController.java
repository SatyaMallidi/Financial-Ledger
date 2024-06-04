package com.example.dataworks.financialledger.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.DTO.FinancialReportDTO;
import com.example.dataworks.financialledger.entity.FinancialReport;
import com.example.dataworks.financialledger.service.FinancialReportService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/public/financial")
public class FinancialReportController {

    @Autowired
    public FinancialReportService financialReportService;
    public FinancialReportController(FinancialReportService financialReportService) {
        this.financialReportService = financialReportService;
    }

     @PostMapping("/")
    public FinancialReport createFinancialReport(@RequestBody FinancialReportDTO financialReportDTO) {
    FinancialReport createdFinancialReport = financialReportService.createFinancialReport(financialReportDTO);
    return createdFinancialReport;
    }


    @GetMapping("/{financialId}")
    public FinancialReport getFinancialReport(@PathVariable Long financialId) {
        FinancialReport financialReport = financialReportService.getFinancialReport(financialId);
        return financialReport;
    }

    @GetMapping("/user/{userId}")
    public List<FinancialReport> getFinancialReportByUserId(@PathVariable Long userId) {
        List<FinancialReport> financialReports = financialReportService.getFinancialReportByUserId(userId);
        return financialReports;
    }

    @DeleteMapping("/{financialId}")
    public void deleteFinancialReportById(@PathVariable Long financialId) {
        financialReportService.deleteFinancialReport(financialId);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteFinancialReportByUserId(@PathVariable Long userId) {
        financialReportService.deleteFinancialReportByUserId(userId);
    }

    @GetMapping("/monthly/{userId}/{year}/{month}")
    public List<FinancialReport> generateMonthlyReport(@PathVariable Long userId, @PathVariable int year,
            @PathVariable int month) {
        return financialReportService.generateMonthlyReport(userId, year, month);
    }

    @GetMapping("/yearly/{userId}/{year}")
    public List<FinancialReport> generateYearlyReport(@PathVariable Long userId, @PathVariable int year) {
        return financialReportService.generateYearlyReport(userId, year);
    }

    @GetMapping("/quarterly/{userId}/{year}/{quarter}")
    public List<FinancialReport> generateQuarterlyReport(@PathVariable Long userId, @PathVariable int year,
            @PathVariable int quarter) {
        return financialReportService.generateQuarterlyReport(userId, year, quarter);
    }

    @PutMapping("/{financialId}")
    public FinancialReport updateFinancialReport(@PathVariable Long financialId, @RequestBody FinancialReport financialReport) {
        return financialReportService.updFinancialReport(financialId, financialReport);
    }

    @PatchMapping("/{financialId}")
    public FinancialReport partiallyUpdateFinancialReport(@PathVariable Long financialId,
            @RequestBody FinancialReport financialReport) {
        FinancialReport existingReport = financialReportService.getFinancialReport(financialId);
        if (financialReport.getNetProfit() != null) {
            existingReport.setNetProfit(financialReport.getNetProfit());
        }
        if (financialReport.getTotalExpenses() != null) {
            existingReport.setTotalExpenses(financialReport.getTotalExpenses());
        }
        if (financialReport.getTotalIncome() != null) {
            existingReport.setTotalIncome(financialReport.getTotalIncome());
        }
        return financialReportService.updFinancialReport(financialId, existingReport);
    }

    @GetMapping("/")
    public List<FinancialReport> getAllFinancialReports() {
        return financialReportService.getAllFinancialReports();
    }
}
