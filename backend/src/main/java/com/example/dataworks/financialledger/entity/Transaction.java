package com.example.dataworks.financialledger.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Double amount;

    @Column(length = 500)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @PrePersist
    private void setDateIfNull() {
        if (date == null) {
            date = LocalDate.now();
        }
    }


}
