package com.example.dataworks.financialledger.service;



import com.example.dataworks.financialledger.entity.User;
import java.util.List;

public interface UserService {

  
    public User createuser(User user);
    public User updateUser(User user, Long id);
    public void deleteUser(Long id);
    public User getUserById(Long id);
    public User getUserByEmailAndId(String email, Long id);
    public List<User> getAllUsers();    
    
}
