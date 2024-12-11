package com.e_sell.controller;

import com.e_sell.domain.UserInfo;
import com.e_sell.repository.UserRepository;
import com.e_sell.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user
        UserInfo user = new UserInfo();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());    
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole("ROLE_USER");
        user.setCreatedAt(new Date(0));
        

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String jwt = jwtUtil.generateToken(request.getUsername());
        
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}

@Data
class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
}

@Data
class LoginRequest {
    private String username;
    private String password;
}

@Data
@AllArgsConstructor
class JwtResponse {
    private String token;
}

@Data
@AllArgsConstructor
class MessageResponse {
    private String message;
}
