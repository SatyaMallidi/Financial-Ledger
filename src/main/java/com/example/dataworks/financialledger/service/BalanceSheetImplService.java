package com.example.dataworks.financialledger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dataworks.financialledger.Exception.BalanceSheetExceptionNotFound;
import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
import com.example.dataworks.financialledger.entity.BalanceSheet;
import com.example.dataworks.financialledger.repository.BalanceSheetRepository;

@Service
public class BalanceSheetImplService implements BalanceSheetService {

    @Autowired
    private BalanceSheetRepository balanceSheetRepository;

    @Override
    @Transactional
    public BalanceSheet saveBalanceSheet(BalanceSheet balanceSheet) {
        BalanceSheet newBalanceSheet = balanceSheetRepository.save(balanceSheet);
        if (newBalanceSheet == null) {
            throw new BalanceSheetExceptionNotFound("BalanceSheet is not saved");
        }
        return newBalanceSheet;
    }

    @Override
    @Transactional(readOnly = true)
    public BalanceSheet getBalanceSheetById(Long balanceId) {
        return balanceSheetRepository.findById(balanceId)
                .orElseThrow(() -> new BalanceSheetExceptionNotFound("BalanceSheet not found with id: " + balanceId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BalanceSheet> getBalanceSheetsByUserId(Long userId) {
        List<BalanceSheet> balanceSheets = balanceSheetRepository.findByUserUserId(userId);
        if (balanceSheets.isEmpty()) {
            throw new BalanceSheetExceptionNotFound("BalanceSheets not found for user with id: " + userId);
        }
        return balanceSheets;
    }

    @Override
    @Transactional
    public void deleteBalanceSheet(Long balanceId) {
        balanceSheetRepository.findById(balanceId)
                .orElseThrow(() -> new BalanceSheetExceptionNotFound("BalanceSheet not found with id: " + balanceId));
        balanceSheetRepository.deleteById(balanceId);
    }

    @Override
    @Transactional
    public void deleteBalanceSheetByUserId(Long userId) {
        List<BalanceSheet> balanceSheets = balanceSheetRepository.findByUserUserId(userId);
        if (balanceSheets.isEmpty()) {
            throw new BalanceSheetExceptionNotFound("Balance sheets not found for user with id: " + userId);
        }
        balanceSheetRepository.deleteAll(balanceSheets);
    }

    @Override
    @Transactional
    public BalanceSheet updateBlanaceSheet(Long balanceId, BalanceSheet balanceSheet) {
        Optional<BalanceSheet> existingBalanceSheet = balanceSheetRepository.findById(balanceId);
        if (!existingBalanceSheet.isPresent()) {
            throw new UserExceptionNotFound("The BalanceSheet is not found");
        }
        return balanceSheetRepository.save(balanceSheet);
    }
}
