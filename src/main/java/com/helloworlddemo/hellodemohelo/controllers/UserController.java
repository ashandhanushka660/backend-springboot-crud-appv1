package com.helloworlddemo.hellodemohelo.controllers;

import com.helloworlddemo.hellodemohelo.model.User;
import com.helloworlddemo.hellodemohelo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Netlify Frontend එකෙන් එන requests පිළිගැනීමට
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Get All Users (Dashboard එකේ Table එකට දත්ත ලබා ගැනීමට)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 2. Register Endpoint (අලුත් කෙනෙක්ව Register කිරීමට)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        User existingUser = userService.findByEmail(newUser.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        userService.registerUser(newUser);
        return ResponseEntity.ok("Registration Successful!");
    }

    // 3. Login Endpoint (යූසර් කෙනෙක් Login කිරීමට)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        User existingUser = userService.findByEmail(loginUser.getEmail());

        if (existingUser == null || !existingUser.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid Email or Password!");
        }

        return ResponseEntity.ok("Login Successful!");
    }

    // 4. Delete Endpoint (ಯූසර් කෙනෙක්ව ඩිලීට් කිරීමට)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully!");
    }
}