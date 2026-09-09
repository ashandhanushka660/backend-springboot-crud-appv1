package com.helloworlddemo.hellodemohelo.controller;

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

    // Register Endpoint එක
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        // 1. මේ email එක දැනටමත් ඩේටාබේස් එකේ තියෙනවද බලනවා
        User existingUser = userRepository.findByEmail(newUser.getEmail());

        // 2. user කෙනෙක් ඉන්නවා නම් පමණක් error එකක් දෙනවා
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        // 3. නැත්නම් අලුත් යූසර්ව ඩේටාබේස් එකට save කරනවා
        userRepository.save(newUser);
        return ResponseEntity.ok("Registration Successful!");
    }
}