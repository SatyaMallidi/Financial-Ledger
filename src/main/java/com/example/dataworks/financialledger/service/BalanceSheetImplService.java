package com.example.dataworks.financialledger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound;
import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
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
            throw new com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound(
                    "BalanceSheet is not saved");
        }
        return newbalancesheet;
    }

    @Override
    public BalanceSheet getBalanceSheetById(Long balanceId) {
        return balanceSheetRepository.findById(balanceId)
                .orElseThrow(() -> new com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound(
                        "BalanceSheet not found with id: " + balanceId));
    }

    @Override
    public List<BalanceSheet> getBalanceSheetsByUserId(Long userId) {
        List<BalanceSheet> balanceSheets = balanceSheetRepository.findByUserUserId(userId);
        if (balanceSheets.isEmpty()) {
            throw new BalanceSheetExceptionNotFound("BalanceSheets not found for user with id: " + userId);
        }
        return balanceSheets;
    }

    @Override
    public void deleteBalanceSheet(Long balanceId) {
        balanceSheetRepository.findById(balanceId)
                .orElseThrow(() -> new BalanceSheetExceptionNotFound("BalanceSheet not found with id: " + balanceId));
        balanceSheetRepository.deleteById(balanceId);
    }

    @Override
    public void deleteBalanceSheetByUserId(Long userId) {
        List<BalanceSheet> balanceSheets = balanceSheetRepository.findByUserUserId(userId);
        if (balanceSheets.isEmpty()) {
            throw new BalanceSheetExceptionNotFound("Balance sheets not found for user with id: " + userId);
        }
        balanceSheetRepository.deleteAll(balanceSheets);
    }

    @Override
    public BalanceSheet updateBlanaceSheet(Long balanceId, BalanceSheet balanceSheet) {
        Optional<BalanceSheet> existingbalancesheet = balanceSheetRepository.findById(balanceId);
        if (existingbalancesheet == null) {
            throw new UserExceptionNotFound("The BalanceSheet is not found");
        }
        return balanceSheetRepository.save(balanceSheet);

    }

}
