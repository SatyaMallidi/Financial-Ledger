package com.example.dataworks.financialledger.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
public class BalanceSheet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balanceId")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double assets;

    @Column(nullable = false)
    private Double liabilities;

    @Column(nullable = false)
    private Double equity;

}
