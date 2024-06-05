package com.example.dataworks.financialledger.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BalanceSheet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balanceId")
    private Long balanceId;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @PrePersist
    private void setDateIfNull() {
        if (date == null) {
            date = LocalDate.now();
        }
    }

}
