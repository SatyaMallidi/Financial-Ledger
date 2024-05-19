package com.example.dataworks.financialledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;  

    @Override
    public User createuser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();  
    }

    @Override
    public User getUserById(Long user_id) {
    return userRepository.findById(user_id).get();
    }

    @Override
    public User getUserByEmailAndId(String email, Long user_id) {
        
        return userRepository.findByEmailAndId(email, user_id);
    }

    @Override
    public User updateUser(Long user_id, User user) {
        
        return userRepository.save(user);
    }
    
}
    
    
