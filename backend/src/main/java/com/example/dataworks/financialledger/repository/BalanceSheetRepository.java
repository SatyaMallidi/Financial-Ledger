package com.example.dataworks.financialledger.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.dataworks.financialledger.entity.BalanceSheet;

@Repository
public interface BalanceSheetRepository extends JpaRepository<BalanceSheet, Long> {
    List<BalanceSheet> findByUserUserId(Long userId);
}

