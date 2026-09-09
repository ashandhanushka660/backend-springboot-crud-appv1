package com.helloworlddemo.hellodemohelo.services;

import com.helloworlddemo.hellodemohelo.model.User;
import com.helloworlddemo.hellodemohelo.repositories.UserRepository; // මෙතැන 'repositories' ලෙස 's' අකුරක් තිබිය යුතුය!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // සියලුම Users ලා ලබා ගැනීම
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // අලුත් User කෙනෙක් Save කිරීම (Register)
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Email එක මඟින් User කෙනෙක් සෙවීම
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // User කෙනෙක්ව ඩිලීට් කිරීම
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}