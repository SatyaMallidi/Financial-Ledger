package com.example.dataworks.financialledger.DTO;
import com.example.dataworks.financialledger.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO {

    private Long userId;
    private LocalDate date;
    private Double amount;
    private String description;
    private TransactionType type;
}

