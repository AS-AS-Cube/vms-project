package com.project.backend.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.user.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ TOTAL USERS (no date filter)
    @Override
    public long getTotalUsersCount() {
        return userRepository.count();
    }
}
