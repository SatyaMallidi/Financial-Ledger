package com.example.dataworks.financialledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dataworks.financialledger.entity.BalanceSheet;
import com.example.dataworks.financialledger.repository.BalanceSheetRepository;

public class BalanceSheetImplService implements BalanceSheetService{
    
    @Autowired
    public BalanceSheetRepository balanceSheetRepository;

    @Override
    public void deleteBalanceSheet(Long id) {
        balanceSheetRepository.deleteById(id);
        
    }

    @Override
    public BalanceSheet getBalanceSheetById(Long id) {
        return balanceSheetRepository.findById(id).get();
    }

    @Override
    public List<BalanceSheet> getBalanceSheetsByUserId(Long user_id) {
        return balanceSheetRepository.findByUserId(user_id);
    }

    @Override
    public BalanceSheet saveBalanceSheet(BalanceSheet balanceSheet) {
        return balanceSheetRepository.save(balanceSheet);
    }
    
}
