package com.example.dataworks.financialledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<BalanceSheet> newBalancesheet(@RequestBody BalanceSheet balanceSheet) {
    balanceSheetService.saveBalanceSheet(balanceSheet);
    if (balanceSheet != null) {
      return ResponseEntity.ok(balanceSheet);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<BalanceSheet> getById(@PathVariable Long id) {
    BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(id);
    if (balanceSheet != null) {
      return ResponseEntity.ok(balanceSheet);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<BalanceSheet>> getByUserId(@PathVariable Long user_id) {
    List<BalanceSheet> balanceSheet = balanceSheetService.getBalanceSheetsByUserId(user_id);
    if (balanceSheet != null) {
      return ResponseEntity.ok(balanceSheet);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BalanceSheet> deleteById(@PathVariable Long id) {
    BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(id);
    if (balanceSheet != null) {
      balanceSheetService.deleteBalanceSheet(id);
      return ResponseEntity.ok(balanceSheet);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<BalanceSheet> deleteByUserId(@PathVariable Long user_id) {
    BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(user_id);
    if (balanceSheet != null) {
      balanceSheetService.deleteBalanceSheetByUserId(user_id);
      return ResponseEntity.ok(balanceSheet);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @PutMapping("/{id}")
  public ResponseEntity<BalanceSheet> updateBalanceSheet(@PathVariable Long id, @RequestBody BalanceSheet balance) {
    BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(id);
    if (balanceSheet != null) {
      balanceSheetService.saveBalanceSheet(balanceSheet);
      return ResponseEntity.ok(balanceSheet);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("{id}")
  public ResponseEntity<BalanceSheet> partiallyUpdateBalanceSheet(@PathVariable Long id,
      @RequestBody BalanceSheet balance) {
    BalanceSheet balanceSheet = balanceSheetService.getBalanceSheetById(id);
    if (balanceSheet != null) {
      if (balance.getAssets() != null) {
        balanceSheet.setAssets(balance.getAssets());
      }
      if (balance.getEquity() != null) {
        balanceSheet.setEquity(balance.getEquity());
      }
      if (balance.getLiabilities() != null) {
        balanceSheet.setLiabilities(balance.getLiabilities());
      }
      BalanceSheet newBalanceSheet = balanceSheetService.saveBalanceSheet(balanceSheet);
      return ResponseEntity.ok(newBalanceSheet);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}