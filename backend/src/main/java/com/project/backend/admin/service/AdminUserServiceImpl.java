package com.project.backend.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.admin.dto.AdminUserRowDTO;
import com.project.backend.booking.repository.BookingRepository;
import com.project.backend.payment.entities.PaymentStatus;
import com.project.backend.payment.repository.PaymentRepository;
import com.project.backend.user.model.User;
import com.project.backend.user.model.UserStatus;
import com.project.backend.user.repository.UserRepository;
import com.project.backend.vehicle.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final VehicleRepository vehicleRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public List<AdminUserRowDTO> getAdminUsers() {

        return userRepository.findAll()
            .stream()
            .map(user -> {

                boolean isVendor = user.getRole().name().equals("ROLE_VENDOR");

                Long activityCount;
                Double activityAmount;

                if (isVendor) {
                    activityCount = vehicleRepository.countVehiclesByVendor(user.getUserId());
                    activityAmount = paymentRepository.vendorRevenue(
                            user.getUserId(),
                            PaymentStatus.SUCCESS
                    );

                } else {
                    activityCount = bookingRepository.countBookingsByUser(user.getUserId());
                    activityAmount = bookingRepository.sumBookingAmountByUser(user.getUserId());
                }

                return AdminUserRowDTO.builder()
                    .userId(user.getUserId())
                    .name(user.getFullName())
                    .email(user.getEmail())
                    .mobileNo(user.getMobileNumber())
                    .role(user.getRole().name().replace("ROLE_", ""))
                    .status(user.getDeletedAt() == null ? "ACTIVE" : "SUSPENDED")
                    .activityCount(activityCount)
                    .activityAmount(activityAmount)
                    .joinedDate(user.getCreatedAt())
                    .build();
            })
            .toList();
    }
    
    @Override
    public void updateStatus(Long userId, UserStatus status) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(status);
    }
}
