package com.project.backend.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.admin.dto.DashboardStatsResponse;

import com.project.backend.booking.service.BookingService;

import com.project.backend.payment.service.PaymentService;

import com.project.backend.user.service.UserService;

import com.project.backend.vehicle.service.VehicleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final UserService userService;
    private final VehicleService vehicleService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    @Override
    public DashboardStatsResponse getDashboardStats() {

    	long totalUsers = userService.getTotalUsersCount();

        long activeVehicles = vehicleService.countActiveVehicles();

        long activeBookings = bookingService.countActiveBookings();
       
        double monthlyRevenue = paymentService.getMonthlyRevenue();
        return DashboardStatsResponse.builder()
            .totalUsers(totalUsers)

            .activeVehicles(activeVehicles)
           
            .activeBookings(activeBookings)
           
            .monthlyRevenue(monthlyRevenue)
            .build();
    }
}
