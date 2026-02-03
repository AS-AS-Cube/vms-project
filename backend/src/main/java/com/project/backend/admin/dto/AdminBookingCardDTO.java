package com.project.backend.admin.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminBookingCardDTO {

    private Long bookingId;
    private String vehicleName;
    private String customerName;
    private String vendorName;

    private LocalDateTime pickupDate;
    private LocalDateTime dropoffDate;

    private String bookingStatus;
    private BigDecimal totalAmount;
}
