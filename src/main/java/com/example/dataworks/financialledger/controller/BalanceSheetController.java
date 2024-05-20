package com.example.dataworks.financialledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.entity.BalanceSheet;
import com.example.dataworks.financialledger.service.BalanceSheetService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/balanceSheet")
public class BalanceSheetController {

    @Autowired
    private BalanceSheetService balanceSheetService;

    @PostMapping("/")
    public BalanceSheet newBalanceSheet(@RequestBody BalanceSheet balanceSheet) {
        return balanceSheetService.saveBalanceSheet(balanceSheet);
    }

    @GetMapping("/{id}")
    public BalanceSheet getById(@PathVariable Long id) {
        BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(id);
        return balanceSheet;
    }

    @GetMapping("/user/{userId}")
    public List<BalanceSheet> getByUserId(@PathVariable Long userId) {
        List<BalanceSheet> balanceSheets = balanceSheetService.getBalanceSheetsByUserId(userId);
        return balanceSheets;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        balanceSheetService.deleteBalanceSheet(id);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteByUserId(@PathVariable Long userId) {
        balanceSheetService.deleteBalanceSheetByUserId(userId);
    }

    @PutMapping("/{id}")
    public BalanceSheet updateBalanceSheet(@PathVariable Long id, @RequestBody BalanceSheet balanceSheet) {
        return balanceSheetService.updateBlanaceSheet(id, balanceSheet);
    }

    @PatchMapping("/{id}")
    public BalanceSheet partiallyUpdateBalanceSheet(@PathVariable Long id, @RequestBody BalanceSheet balanceSheet) {
        BalanceSheet existingBalanceSheet = balanceSheetService.getBalanceSheetById(id);
        if (balanceSheet.getAssets() != null) {
            existingBalanceSheet.setAssets(balanceSheet.getAssets());
        }
        if (balanceSheet.getEquity() != null) {
            existingBalanceSheet.setEquity(balanceSheet.getEquity());
        }
        if (balanceSheet.getLiabilities() != null) {
            existingBalanceSheet.setLiabilities(balanceSheet.getLiabilities());
        }
        BalanceSheet updatedBalanceSheet = balanceSheetService.updateBlanaceSheet(id, existingBalanceSheet);
        return updatedBalanceSheet;

    }
}
