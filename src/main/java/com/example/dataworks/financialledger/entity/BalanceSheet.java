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
public class BalanceSheet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double assets;

    @Column(nullable = false)
    private Double liabilities;

    @Column(nullable = false)
    private Double equity;

    public BalanceSheet(Long id, User user, LocalDate date, Double assets, Double liabilities, Double equity) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.assets = assets;
        this.liabilities = liabilities;
        this.equity = equity;
    }

    public BalanceSheet() {
    }
    
      @PrePersist
    protected void onCreate() {
        if (this.date == null) {
            this.date = LocalDate.now();
        }
    }
}


