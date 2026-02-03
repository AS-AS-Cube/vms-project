package com.project.backend.auth.service;

import com.project.backend.auth.dto.LoginRequest;
import com.project.backend.auth.dto.LoginResponse;
import com.project.backend.auth.dto.RegisterRequest;
import com.project.backend.auth.jwt.JwtUtil;
import com.project.backend.user.UserRole;
import com.project.backend.user.model.User;
import com.project.backend.user.model.UserStatus;
import com.project.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        // 1. Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // 2. Create User entity
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // 🔐 encrypted
                .mobileNumber(request.getMobileNumber())
                .city(request.getCity())
                .role(request.getRole())
                .build();
        
        if (request.getRole() == UserRole.ROLE_CUSTOMER) {
            user.setStatus(UserStatus.ACTIVE);
        } else if (request.getRole() == UserRole.ROLE_VENDOR) {
            user.setStatus(UserStatus.PENDING);
        }

        // 3. Save user
        userRepository.save(user);
    }
    
    public LoginResponse login(LoginRequest request) {
    	User user = userRepository.findByEmail(request.getEmail())
    			.orElseThrow(()-> new RuntimeException());
    
    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
    	throw new RuntimeException("Invalid email or password");
    }
    
    String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    
    return new LoginResponse(token, user.getRole().name());
    
}
}
