package com.example.dataworks.financialledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound;
import com.example.dataworks.financialledger.entity.BalanceSheet;
import com.example.dataworks.financialledger.repository.BalanceSheetRepository;

@Service
public class BalanceSheetImplService implements BalanceSheetService{
    
    

    @Autowired
    public BalanceSheetRepository balanceSheetRepository;

    @Override
    public void deleteBalanceSheet(Long id) {
        balanceSheetRepository.findById(id).orElseThrow(() ->
            new BalanceSheetExceptionNotFound("Balance sheet not found with id: " + id));
        balanceSheetRepository.deleteById(id);
    }
    

    @Override
    public BalanceSheet getBalanceSheetById(Long id) {
        return balanceSheetRepository.findById(id).orElseThrow(()->new BalanceSheetExceptionNotFound("BalanceSheet not found"+id));
    }

    @Override
    public List<BalanceSheet> getBalanceSheetsByUserId(Long user_id) {
        List<BalanceSheet> balancesheet =   balanceSheetRepository.findByUserId(user_id);
        if(balancesheet!=null){
            return balancesheet;
        } else{
            throw new BalanceSheetExceptionNotFound("BalanceSheets not found");
        }
    }

    @Override
    public BalanceSheet saveBalanceSheet(BalanceSheet balanceSheet) {
        BalanceSheet newbalancesheet = balanceSheetRepository.save(balanceSheet);
        if(newbalancesheet == null){
           throw new BalanceSheetExceptionNotFound("BalanceSheet is not saved");
        }
        return newbalancesheet;
    }

    @Override
    public void deleteBalanceSheetByUserId(Long user_id) {
        List<BalanceSheet> balanceSheets = balanceSheetRepository.findByUserId(user_id);
        if (balanceSheets.isEmpty()) {
            throw new BalanceSheetExceptionNotFound("Balance sheets not found for user with id: " + user_id);
        }
        balanceSheetRepository.deleteAll(balanceSheets);
    }
    
}
