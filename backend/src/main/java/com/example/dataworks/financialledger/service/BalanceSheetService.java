package com.example.dataworks.financialledger.service;

import java.util.List;

import com.example.dataworks.financialledger.entity.BalanceSheet;

public interface BalanceSheetService {
   public BalanceSheet saveBalanceSheet(BalanceSheet balanceSheet);

   public BalanceSheet getBalanceSheetById(Long balanceId);

   public List<BalanceSheet> getBalanceSheetsByUserId(Long userId);

   public void deleteBalanceSheet(Long balanceId);

   public void deleteBalanceSheetByUserId(Long userId);

   public BalanceSheet updateBlanaceSheet(Long balanceId, BalanceSheet balanceSheet);
}
