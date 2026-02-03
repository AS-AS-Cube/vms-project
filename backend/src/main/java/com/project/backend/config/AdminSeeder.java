package com.project.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.backend.user.UserRole;
import com.project.backend.user.model.User;
import com.project.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        String adminEmail = "admin@email.com";

        // 🔴 MOST IMPORTANT CHECK
        if (userRepository.existsByEmail(adminEmail)) {
            // admin already exists → DO NOTHING
            return;
        }

        // ✅ Create admin ONLY ONCE
        User admin = User.builder()
                .fullName("System Admin")
                .email(adminEmail)
                .password(passwordEncoder.encode("admin123"))
                .mobileNumber("9999999999")
                .city("System")
                .role(UserRole.ROLE_ADMIN)
                .build();

        userRepository.save(admin);
    }
}

