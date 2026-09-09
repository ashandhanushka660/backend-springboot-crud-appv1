package com.helloworlddemo.hellodemohelo.controllers;

import com.helloworlddemo.hellodemohelo.model.User;
import com.helloworlddemo.hellodemohelo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) // CORS error එක නැති කිරීමට Next.js URL එක ලබා දීම
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Create / Register API
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    // 2. Read / Get All Users API
    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // 3. Update API (PUT Request)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // 4. Delete API (DELETE Request)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}