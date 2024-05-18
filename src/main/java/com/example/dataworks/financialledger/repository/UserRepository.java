package com.example.dataworks.financialledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dataworks.financialledger.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

<<<<<<< HEAD
    User findByEmailAndId(String email, Long id);
=======
  User findByEmailAndId(String email, Long id);

>>>>>>> 6db61c53ec83aecc217f4e7f980a28fcc1389129
    
}
