package com.example.dataworks.financialledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    


    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return createdUser;
    }
   

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return user;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return updatedUser;
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUser(userId);

    }

    @PatchMapping("/{userId}")
    public User partialUdateUser(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.getUserById(userId);
        if (user.getUsername() != null) {
            updatedUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            updatedUser.setPassword(user.getPassword());
        }
        if (user.getEmail() != null) {
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getRole() != null) {
            updatedUser.setRole(user.getRole());
        }
        return userService.createUser(updatedUser);

    }
}
