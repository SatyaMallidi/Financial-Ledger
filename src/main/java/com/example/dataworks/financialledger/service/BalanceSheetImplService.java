package com.example.dataworks.financialledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound;
import com.example.dataworks.financialledger.entity.BalanceSheet;
import com.example.dataworks.financialledger.repository.BalanceSheetRepository;

@Service
public class BalanceSheetImplService implements BalanceSheetService {
    
    @Autowired
    private BalanceSheetRepository balanceSheetRepository;

    @Override
    public BalanceSheet saveBalanceSheet(BalanceSheet balanceSheet) {
        BalanceSheet newbalancesheet = balanceSheetRepository.save(balanceSheet);
        if (newbalancesheet == null) {
            throw new com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound("BalanceSheet is not saved");
        }
        return newbalancesheet;
    }

    @Override
    public BalanceSheet getBalanceSheetById(Long id) {
        return balanceSheetRepository.findById(id)
                .orElseThrow(() -> new com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound("BalanceSheet not found with id: " + id));
    }

    @Override
    public List<BalanceSheet> getBalanceSheetsByUserId(Long userId) {
    List<BalanceSheet> balanceSheets = balanceSheetRepository.findByUserUserId(userId);
    if (balanceSheets.isEmpty()) {
        throw new com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound("BalanceSheets not found for user with id: " + userId);
    }
    return balanceSheets;
}


    @Override
    public void deleteBalanceSheet(Long id) {
        balanceSheetRepository.findById(id)
                .orElseThrow(() -> new BalanceSheetExceptionNotFound("BalanceSheet not found with id: " + id));
        balanceSheetRepository.deleteById(id);
    }

    @Override
    public void deleteBalanceSheetByUserId(Long userId) {
        List<BalanceSheet> balanceSheets = balanceSheetRepository.findByUserUserId(userId);
        if (balanceSheets.isEmpty()) {
            throw new BalanceSheetExceptionNotFound("Balance sheets not found for user with id: " + userId);
        }
        balanceSheetRepository.deleteAll(balanceSheets);
    }

}
