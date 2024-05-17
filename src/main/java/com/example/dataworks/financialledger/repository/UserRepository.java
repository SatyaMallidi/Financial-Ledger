package com.example.dataworks.financialledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataworks.financialledger.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndId(String email, Long id);

    
}
