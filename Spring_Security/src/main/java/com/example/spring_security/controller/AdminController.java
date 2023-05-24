package com.example.spring_security.controller;

import com.example.spring_security.model.User;
import com.example.spring_security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secure/auth")
public class AdminController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/admin/add")
    public String addUser(@RequestBody User user)
    {
        String pwd= user.getPassword();
        String encrptedPwd = passwordEncoder.encode(pwd);
        user.setPassword(encrptedPwd);         //set the password in user object
        user.setRole(user.getRole());
        userRepository.save(user);
        return "user Added Successfully";

    }
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
