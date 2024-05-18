package com.example.dataworks.financialledger.service;

import java.util.List;

import com.example.dataworks.financialledger.entity.BalanceSheet;

public interface BalanceSheetService {
   public BalanceSheet saveBalanceSheet(BalanceSheet balanceSheet);
   public BalanceSheet getBalanceSheetById(Long id);
   public List<BalanceSheet> getBalanceSheetsByUserId(Long user_id);
   public void deleteBalanceSheet(Long id);
   public void deleteBalanceSheetByUserId(Long user_id);
}
