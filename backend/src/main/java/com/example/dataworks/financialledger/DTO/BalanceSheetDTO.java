package com.example.dataworks.financialledger.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BalanceSheetDTO {

    private Long userId;
    private LocalDate date;
    private Double assets;
    private Double liabilities;
    private Double equity;
}
