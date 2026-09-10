package com.helloworlddemo.hellodemohelo.controllers;

import com.helloworlddemo.hellodemohelo.model.User;
import com.helloworlddemo.hellodemohelo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "${app.frontend.url}")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    // 1. Get All Users (ඩේටාබේස් එකේ සියලුම Users ලා ලබා ගැනීම)
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    // 2. Register Endpoint (අලුත් කෙනෙක්ව Register කිරීම)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        User existingUser = userService.findByEmail(newUser.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists!");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.registerUser(newUser);
        return ResponseEntity.ok("Registration Successful!");
    }

    // 3. Login Endpoint (යූසර් කෙනෙක් Login කිරීම)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        User existingUser = userService.findByEmail(loginUser.getEmail());

        if (existingUser == null || !isValidPassword(loginUser.getPassword(), existingUser)) {
            return ResponseEntity.status(401).body("Invalid Email or Password!");
        }

        return ResponseEntity.ok("Login Successful!");
    }

    // 4. Delete Endpoint (යූසර් කෙනෙක්ව Delete කිරීම)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully!");
    }

    private boolean isValidPassword(String rawPassword, User user) {
        if (user.getPassword().startsWith("$2a$") || user.getPassword().startsWith("$2b$")) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }

        if (user.getPassword().equals(rawPassword)) {
            user.setPassword(passwordEncoder.encode(rawPassword));
            userService.registerUser(user);
            return true;
        }
        return false;
    }

    public record UserResponse(Long id, String name, String email) {
    }
}