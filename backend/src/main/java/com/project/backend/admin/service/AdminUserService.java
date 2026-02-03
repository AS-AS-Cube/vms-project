package com.project.backend.admin.service;

import java.util.List;

import com.project.backend.admin.dto.AdminUserRowDTO;
import com.project.backend.user.model.UserStatus;

public interface AdminUserService {
    List<AdminUserRowDTO> getAdminUsers();
    
    void updateStatus(Long userId, UserStatus status);
}

