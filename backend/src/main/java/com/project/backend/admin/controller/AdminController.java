package com.project.backend.admin.controller;




import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.backend.admin.dto.AdminUserRowDTO;
import com.project.backend.admin.dto.DashboardStatsResponse;
import com.project.backend.admin.service.AdminDashboardService;
import com.project.backend.admin.service.AdminUserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminDashboardService dashboardService;
    
    private final AdminUserService adminUserService;

    @GetMapping("/dashboard/stats")
    public ResponseEntity<DashboardStatsResponse> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<AdminUserRowDTO>> getUsers() {
        return ResponseEntity.ok(adminUserService.getAdminUsers());
    }

}


