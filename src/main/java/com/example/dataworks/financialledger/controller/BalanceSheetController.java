package com.example.dataworks.financialledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.entity.BalanceSheet;
import com.example.dataworks.financialledger.service.BalanceSheetService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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

  @GetMapping("/user/{id}")
  public List<BalanceSheet> getByUserId(@PathVariable Long user_id) {
    List<BalanceSheet> balanceSheet = balanceSheetService.getBalanceSheetsByUserId(user_id);
      return balanceSheet;
  }
  

  @DeleteMapping("/{id}")
  public void  deleteById(@PathVariable Long id) {
    balanceSheetService.deleteBalanceSheet(id);
    
  }

  @DeleteMapping("/user/{id}")
  public void deleteByUserId(@PathVariable Long user_id) {
      balanceSheetService.deleteBalanceSheetByUserId(user_id);
      
    

  }

  @PutMapping("/{id}")
  public BalanceSheet updateBalanceSheet(@PathVariable Long id, @RequestBody BalanceSheet balance) {
    BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(id);
      balanceSheetService.saveBalanceSheet(balanceSheet);
      return balanceSheet;
      
    
  }

  @PatchMapping("{id}")
  public BalanceSheet partiallyUpdateBalanceSheet(@PathVariable Long id,@RequestBody BalanceSheet balance) {
    BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(id);
      if (balance.getAssets() != null) {
        balanceSheet.setAssets(balance.getAssets());
      }
      if (balance.getEquity() != null) {
        balanceSheet.setEquity(balance.getEquity());
      }
      if (balance.getLiabilities() != null) {
        balanceSheet.setLiabilities(balance.getLiabilities());
      }
      BalanceSheet newBalanceSheet= balanceSheetService.saveBalanceSheet(balanceSheet);
      return newBalanceSheet;
    }
  }