package com.project.backend.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.backend.admin.dto.UserManagementStatsResponse;
import com.project.backend.admin.service.AdminUserService;
import com.project.backend.admin.service.UserManagementStatsService;
import com.project.backend.user.model.UserStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserManagementStatsService statsService;
    
    private final AdminUserService adminUserService;

    @GetMapping("/stats")
    public ResponseEntity<UserManagementStatsResponse> getUserStats() {
        return ResponseEntity.ok(statsService.getUserManagementStats());
    }
    
    @PutMapping("/{id}/approve")
    public void approveUser(@PathVariable Long id) {
        adminUserService.updateStatus(id, UserStatus.ACTIVE);
    }

    @PutMapping("/{id}/reject")
    public void rejectUser(@PathVariable Long id) {
        adminUserService.updateStatus(id, UserStatus.SUSPENDED);
    }
}
