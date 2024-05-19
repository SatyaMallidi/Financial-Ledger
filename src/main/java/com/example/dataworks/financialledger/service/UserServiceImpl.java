package com.example.dataworks.financialledger.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dataworks.financialledger.Exception.UserExceptionNotFound;
import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;  

    @Override
    public User createUser(User user) {
        User newuser=userRepository.save(user);
        if(newuser==null){
            throw new UserExceptionNotFound("The user is not saved");
        }
        return newuser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
        .orElseThrow(()->new UserExceptionNotFound("User is not found"));
        userRepository.deleteById(userId)
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();  
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getUserByEmailAndId(String email, Long userId) {
        return userRepository.findByEmailAndUserId(email, userId);
    }

    @Override
    public User updateUser(Long userId, User user) {
        // You may need to handle user not found scenario here
        return userRepository.save(user);
    }

}
