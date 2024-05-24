package com.example.dataworks.financialledger.service;

import java.util.List;
import java.util.Optional;

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
        User newuser = userRepository.save(user);
        if (newuser == null) {
            throw new UserExceptionNotFound("The user is not saved");
        }
        return newuser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserExceptionNotFound("User is not found"));
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users == null) {
            throw new UserExceptionNotFound("The user is not found");
        }
        return users;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserExceptionNotFound("The userId is not found"));
    }

    @Override
    public User getUserByEmailAndId(String email, Long userId) {
        return userRepository.findByEmailAndUserId(email, userId);
    }

    @Override
    public User updateUser(Long userId, User user) {
        Optional<User> existinguser = userRepository.findById(userId);
        if (existinguser == null) {
            throw new UserExceptionNotFound("The user is not found");
        }
        return userRepository.save(user);
    }

    @Override
    public Object findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    

}
