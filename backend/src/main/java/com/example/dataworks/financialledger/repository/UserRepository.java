package com.example.dataworks.financialledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dataworks.financialledger.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmailAndUserId(String email, Long userId);

  User findByUsername(String username);
    
}
