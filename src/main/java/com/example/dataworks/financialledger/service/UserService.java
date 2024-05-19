package com.example.dataworks.financialledger.service;



import com.example.dataworks.financialledger.entity.User;
import java.util.List;

public interface UserService {

  
    public User createuser(User user);
    public void deleteUser(Long user_id);
    public User getUserById(Long user_id);
    public User getUserByEmailAndId(String email, Long user_id);
    public List<User> getAllUsers();
    public User updateUser(Long user_id, User user);    
    
}
