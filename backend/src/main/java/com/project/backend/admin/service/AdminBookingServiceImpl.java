package com.project.backend.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.admin.dto.AdminBookingCardDTO;
import com.project.backend.admin.dto.AdminBookingStatsDTO;
import com.project.backend.booking.entities.Booking;
import com.project.backend.booking.entities.BookingStatus;
import com.project.backend.booking.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminBookingServiceImpl implements AdminBookingService {

    private final BookingRepository bookingRepository;

    @Override
    public AdminBookingStatsDTO getBookingStats() {

        return AdminBookingStatsDTO.builder()
                .totalBookings(bookingRepository.count())
                .activeBookings(bookingRepository.countActiveBookings())
                .pendingApprovals(bookingRepository.countPendingApprovals())
                .build();
    }

    @Override
    public List<AdminBookingCardDTO> getAllBookings() {

        return bookingRepository.findAllForAdmin()
                .stream()
                .map(b -> AdminBookingCardDTO.builder()
                        .bookingId(b.getBookingId())
                        .vehicleName(b.getVehicle().getName())
                        .customerName(b.getCustomer().getFullName())
                        .vendorName(
                            b.getVehicle()
                             .getVendorId()
                             .getUser()
                             .getFullName()
                        )
                        .pickupDate(b.getPickupDateTime())
                        .dropoffDate(b.getDropoffDateTime())
                        .bookingStatus(b.getStatus().name())
                        .totalAmount(b.getTotalAmount())
                        .build()
                )
                .collect(Collectors.toList());
    }
    
    @Override
    public void approveBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Only pending bookings can be approved");
        }

        booking.setStatus(BookingStatus.CONFIRMED);
    }

    @Override
    public void rejectBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new RuntimeException("Only pending bookings can be rejected");
        }

        booking.setStatus(BookingStatus.CANCELLED);
    }

}
