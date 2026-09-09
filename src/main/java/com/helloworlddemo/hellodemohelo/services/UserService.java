package com.helloworlddemo.hellodemohelo.services;

import com.helloworlddemo.hellodemohelo.model.User;
import com.helloworlddemo.hellodemohelo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 1. Create (Register)
    public User register(User user) {
        return userRepository.save(user);
    }

    // 2. Read (Get All Users)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3. Update (User කෙනෙක්ගේ විස්තර වෙනස් කිරීම)
    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    // 4. Delete (User කෙනෙක්ව ඉවත් කිරීම)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}