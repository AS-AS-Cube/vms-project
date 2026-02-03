package com.project.backend.admin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminBookingStatsDTO {
    private long totalBookings;
    private long activeBookings;
    private long pendingApprovals;
}
