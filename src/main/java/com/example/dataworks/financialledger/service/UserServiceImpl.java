package com.example.dataworks.financialledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.repository.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;  

    @Override
    public User createuser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
        
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByEmailAndId(String email, Long id) {
        
        return userRepository.findByEmailAndId(email, id);
    }

    @Override
    public User updateUser(Long id, User user) {
        
        return userRepository.save(user);
    }
    
    
}
    
    
