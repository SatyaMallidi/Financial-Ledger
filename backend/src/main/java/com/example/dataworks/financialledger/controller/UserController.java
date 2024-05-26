package com.example.dataworks.financialledger.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dataworks.financialledger.entity.AuthenticationResponse;
import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.service.AuthenticationService;
import com.example.dataworks.financialledger.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody User request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody User request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/logout")
    public String logout(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "Invalid user details.";
        }
        
        User user = userService.findByUsername(userDetails.getUsername());
        if (user == null) {
            return "User not found.";
        }

        authenticationService.revokeAllTokenByUser(user);
        return "Logged out successfully.";
    }

    @PostMapping("/refresh_token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
      authenticationService.refreshToken(request, response);
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
