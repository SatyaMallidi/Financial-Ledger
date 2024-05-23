package com.example.dataworks.financialledger.service;

import com.example.dataworks.financialledger.entity.User;
import java.util.List;

public interface UserService {

    User createUser(User user);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    User getUserByEmailAndId(String email, Long userId);

    List<User> getAllUsers();

    User updateUser(Long userId, User user);

}
