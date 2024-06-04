package com.example.dataworks.financialledger.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dataworks.financialledger.DTO.FinancialReportDTO;
import com.example.dataworks.financialledger.Exception.FinancialReportExceptionNotFound;
import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
import com.example.dataworks.financialledger.entity.FinancialReport;
import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.repository.FinancialRepository;
import com.example.dataworks.financialledger.repository.UserRepository;

@Service
public class FinancialReportImplService implements FinancialReportService {

    private FinancialRepository financialRepository;
    private UserRepository userRepository;

    public FinancialReportImplService(FinancialRepository financialRepository, UserRepository userRepository) {
        this.financialRepository = financialRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public FinancialReport saveFinancialReport(FinancialReport financialReport) {
        FinancialReport newFinancialReport = financialRepository.save(financialReport);
        if (newFinancialReport == null) {
            throw new FinancialReportExceptionNotFound("FinancialReport is not saved");
        }
        return newFinancialReport;
    }

    @Override
    @Transactional(readOnly = true)
    public FinancialReport getFinancialReport(Long financialId) {
        return financialRepository.findById(financialId)
                .orElseThrow(() -> new FinancialReportExceptionNotFound("FinancialReport is not found"));
    }

    @Override
    @Transactional
    public void deleteFinancialReport(Long financialId) {
        financialRepository.findById(financialId)
                .orElseThrow(() -> new FinancialReportExceptionNotFound("FinancialReport is not deleted"));
        financialRepository.deleteById(financialId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinancialReport> getFinancialReportByUserId(Long userId) {
        List<FinancialReport> financialReports = financialRepository.findByUserUserId(userId);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReport is not found");
        }
        return financialReports;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinancialReport> generateMonthlyReport(Long userId, int year, int month) {
        List<FinancialReport> financialReports = financialRepository.findMonthlyReports(userId, year, month);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports are not found");
        }
        return financialReports;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinancialReport> generateYearlyReport(Long userId, int year) {
        List<FinancialReport> financialReports = financialRepository.findYearlyReports(userId, year);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports are not found");
        }
        return financialReports;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinancialReport> generateQuarterlyReport(Long userId, int year, int quarter) {
        List<FinancialReport> financialReports = financialRepository.findQuarterlyReports(userId, year, quarter);
        if (financialReports == null) {
            throw new FinancialReportExceptionNotFound("FinancialReports are not found");
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
    @Transactional
    public FinancialReport updFinancialReport(Long financialId, FinancialReport financialReport) {
        Optional<FinancialReport> existingfinancialreport = financialRepository.findById(financialId);
        if (!existingfinancialreport.isPresent()) {
            throw new UserExceptionNotFound("The FinancialReport is not found");
        }
        return financialRepository.save(financialReport);
    }

    @Override
public FinancialReport createFinancialReport(FinancialReportDTO financialReportDTO) {
    User user = userRepository.findById(financialReportDTO.getUserId())
                              .orElseThrow(() -> new RuntimeException("User not found"));
    FinancialReport financialReport = new FinancialReport();
    financialReport.setUser(user);
    financialReport.setPeriodStart(financialReportDTO.getPeriodStart());
    financialReport.setPeriodEnd(financialReportDTO.getPeriodEnd());
    financialReport.setNetProfit(financialReportDTO.getNetProfit());
    financialReport.setTotalIncome(financialReportDTO.getTotalIncome());
    financialReport.setTotalExpenses(financialReportDTO.getTotalExpenses());
    return financialRepository.save(financialReport);
}

@Override
@Transactional(readOnly = true)
public List<FinancialReport> getAllFinancialReports() {
    List<FinancialReport> financialReports = financialRepository.findAll();
    if (financialReports == null) {
        throw new UserExceptionNotFound("The FinancialReport is not found");
    }
    return financialReports;
}

}