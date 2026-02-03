package com.project.backend.admin.service;

import java.util.List;

import com.project.backend.admin.dto.AdminBookingCardDTO;
import com.project.backend.admin.dto.AdminBookingStatsDTO;

public interface AdminBookingService {

    AdminBookingStatsDTO getBookingStats();

    List<AdminBookingCardDTO> getAllBookings();
    
    void approveBooking(Long bookingId);

    void rejectBooking(Long bookingId);
}
