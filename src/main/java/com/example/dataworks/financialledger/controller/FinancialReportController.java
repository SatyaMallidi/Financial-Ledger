package com.example.dataworks.financialledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.entity.FinancialReport;
import com.example.dataworks.financialledger.service.FinancialReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/financial")


public class FinancialReportController {
    @Autowired
    public FinancialReportService financialReportService;

    @PostMapping("/")
    public ResponseEntity<FinancialReport> newFinancialReport(@RequestBody FinancialReport financialReport){
        if(financialReport!=null){
            financialReportService.createFinancialReport(financialReport);
            return  ResponseEntity.ok(financialReport);
        }else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity <FinancialReport> getFinancialReport(@PathVariable Long id ) {
        FinancialReport financialReport=financialReportService.getFinancialReport(id);
        if(financialReport!=null){
            return  ResponseEntity.ok(financialReport);
        }else {
            return ResponseEntity.notFound().build();
        }
        
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<FinancialReport>> getFinancialReportByUserId(@PathVariable Long user_id ) {
        List<FinancialReport> financialReport=financialReportService.getFinancialReportByUserId(user_id);
        if(financialReport!=null){
            return  ResponseEntity.ok(financialReport);
        }else {
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FinancialReport> deleteFinancialReportById(@PathVariable Long id) {
    FinancialReport financialReport = financialReportService.getFinancialReport(id);
    if (financialReport != null) {
      financialReportService.deleteFinancialReport(id);
      return ResponseEntity.ok(financialReport);
    } else {
      return ResponseEntity.notFound().build();
    }
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<FinancialReport> deleteFinancialReportByUserId(@PathVariable Long user_id) {
    FinancialReport financialReport = financialReportService.getFinancialReport(user_id);
    if (financialReport != null) {
      financialReportService.deleteFinancialReport(user_id);
      return ResponseEntity.ok(financialReport);
    } else {
      return ResponseEntity.notFound().build();
    }
    }
    @GetMapping("/monthly/{userId}/{year}/{month}")
    public ResponseEntity<List<FinancialReport>> generateMonthlyReport(@PathVariable Long userId, @PathVariable int year, @PathVariable int month) {
        List<FinancialReport> reports = financialReportService.generateMonthlyReport(userId, year, month);
       if(reports!=null){
        return ResponseEntity.ok(reports);
    }
    else{
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/yearly/{userId}/{year}")
    public ResponseEntity<List<FinancialReport>> generateYearlyReport(@PathVariable Long userId, @PathVariable int year) {
        List<FinancialReport> reports = financialReportService.generateYearlyReport(userId, year);
        if(reports!=null){
            return ResponseEntity.ok(reports);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/quarterly/{userId}/{year}/{quarter}")
    public ResponseEntity<List<FinancialReport>> generateQuarterlyReport(@PathVariable Long userId, @PathVariable int year, @PathVariable int quarter) {
        List<FinancialReport> reports = financialReportService.generateQuarterlyReport(userId, year, quarter);
        if(reports!=null){
            return ResponseEntity.ok(reports);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

@PutMapping("/{id}")
public ResponseEntity<FinancialReport> updateFinancialReport(@PathVariable Long id,@RequestBody FinancialReport finance){
FinancialReport financialReport = financialReportService.getFinancialReport(id);
if(financialReport != null){
    financialReportService.createFinancialReport(finance);
    return ResponseEntity.ok(financialReport);
}else{
    return ResponseEntity.notFound().build();
}
}
@PatchMapping("/{id}")
public ResponseEntity<FinancialReport> partiallyUpdateFinancialReport(@PathVariable Long id,@RequestBody FinancialReport finance){
    FinancialReport financialReport = financialReportService.getFinancialReport(id);
    if(financialReport != null){
        if(finance.getNetProfit() !=null){
            financialReport.setNetProfit(finance.getNetProfit());
        }
        if(finance.getTotalExpenses()!=null){
            financialReport.setTotalExpenses(finance.getTotalExpenses());
        }
        if(finance.getTotalIncome()!=null){
            financialReport.setTotalIncome(finance.getTotalIncome());
        }
        FinancialReport newFinacialReport = financialReportService.createFinancialReport(finance);
        return ResponseEntity.ok(newFinacialReport);
    }else{
        return ResponseEntity.notFound().build();
    }
    }
}

                            