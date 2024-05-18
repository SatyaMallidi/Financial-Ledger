package com.example.dataworks.financialledger.service;



import com.example.dataworks.financialledger.entity.User;
import java.util.List;

public interface UserService {

  
    public User createuser(User user);
    public void deleteUser(Long id);
    public User getUserById(Long id);
    public User getUserByEmailAndId(String email, Long id);
    public List<User> getAllUsers();
    public User updateUser(Long id, User user);    
    
}
