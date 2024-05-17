package com.example.dataworks.financialledger.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Transaction  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Transaction_id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double amount;

    @Column(length = 500)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(Long transaction_id, LocalDate date, Double amount, String description, User user,
            TransactionType type) {
        Transaction_id = transaction_id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.user = user;
        this.type = type;
    }

    public Transaction() {
    }

}
