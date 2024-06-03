package com.example.dataworks.financialledger.DTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FinancialReportDTO {

    private Long userId;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private Double netProfit;
    private Double totalIncome;
    private Double totalExpenses;
}

