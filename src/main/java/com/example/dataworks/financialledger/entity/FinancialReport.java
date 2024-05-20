package com.example.dataworks.financialledger.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class FinancialReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "financialId")
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
  
    @PrePersist
    private void setDefaultDatesIfNeeded() {
        LocalDate now = LocalDate.now();
        if (periodStart == null) {
            periodStart = now;
        }
        if (periodEnd == null) {
            periodEnd = now;
        }
    }

}


