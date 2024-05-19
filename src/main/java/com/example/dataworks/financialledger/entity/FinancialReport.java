package com.example.dataworks.financialledger.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class FinancialReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate periodStart;

    @Column(nullable = false)
    private LocalDate periodEnd;

    @Column(nullable = false)
    private Double netProfit;

    @Column(nullable = false)
    private Double totalIncome;

    @Column(nullable = false)
    private Double totalExpenses;

    public FinancialReport(Long id, User user, LocalDate periodStart, LocalDate periodEnd, Double netProfit,
            Double totalIncome, Double totalExpenses) {
        this.id = id;
        this.user = user;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.netProfit = netProfit;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
    }

    public FinancialReport() {
    }

      @PrePersist
    protected void onCreate() {
        if (this.periodStart == null) {
            this.periodStart = LocalDate.now();
        }
        if (this.periodEnd == null) {
            this.periodEnd = LocalDate.now();
        }

    }
}

