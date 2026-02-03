package com.project.backend.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.admin.dto.AdminVehicleRowDTO;
import com.project.backend.admin.dto.AdminVehicleStatsDTO;
import com.project.backend.admin.service.AdminVehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/vehicles")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminVehicleController {

    private final AdminVehicleService adminVehicleService;

    @GetMapping("/stats")
    public ResponseEntity<AdminVehicleStatsDTO> getVehicleStats() {
        return ResponseEntity.ok(adminVehicleService.getVehicleStats());
    }
    
    @GetMapping
    public ResponseEntity<List<AdminVehicleRowDTO>> getAllVehicles() {
        return ResponseEntity.ok(adminVehicleService.getAllVehicles());
    }
    
    @PutMapping("/{vehicleId}/approve")
    public ResponseEntity<Void> approveVehicle(@PathVariable Long vehicleId) {
        adminVehicleService.approveVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{vehicleId}/reject")
    public ResponseEntity<Void> rejectVehicle(@PathVariable Long vehicleId) {
        adminVehicleService.rejectVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }
}
