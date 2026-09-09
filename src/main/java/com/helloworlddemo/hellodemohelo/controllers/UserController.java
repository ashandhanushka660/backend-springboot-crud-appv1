package com.helloworlddemo.hellodemohelo.controllers;

import com.helloworlddemo.hellodemohelo.model.User;
import com.helloworlddemo.hellodemohelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Netlify Frontend එකෙන් එන requests පිළිගැනීමට
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Login Endpoint එක (Database එකෙන් email/password චෙක් කිරීම)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        // Database එකෙන් email එක හරහා user කෙනෙක් ඉන්නවද බලනවා
        User existingUser = userRepository.findByEmail(loginUser.getEmail());

        // User නැත්නම් හෝ Password එක වැරදි නම් 401 Unauthorized දෙනවා
        if (existingUser == null || !existingUser.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid Email or Password! User not found in database.");
        }

        // සාර්ථක නම් Login Successful දෙනවා
        return ResponseEntity.ok("Login Successful!");
    }
}