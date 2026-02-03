package com.project.backend.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.project.backend.admin.dto.AdminBookingCardDTO;
import com.project.backend.admin.dto.AdminBookingStatsDTO;
import com.project.backend.admin.service.AdminBookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminBookingController {

    private final AdminBookingService adminBookingService;

    @GetMapping("/stats")
    public ResponseEntity<AdminBookingStatsDTO> getBookingStats() {
        return ResponseEntity.ok(adminBookingService.getBookingStats());
    }

    @GetMapping
    public ResponseEntity<List<AdminBookingCardDTO>> getAllBookings() {
        return ResponseEntity.ok(adminBookingService.getAllBookings());
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        adminBookingService.approveBooking(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        adminBookingService.rejectBooking(id);
        return ResponseEntity.ok().build();
    }
}
