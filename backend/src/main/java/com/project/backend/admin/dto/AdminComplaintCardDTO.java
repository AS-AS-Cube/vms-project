package com.project.backend.admin.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminComplaintCardDTO {

    private Long complaintId;
    private String subject;
    private String description;

    private String status;

    private String userName;
    private String userRole;

    private String vehicleName;
    private String vendorName;

    private String bookingRef;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String adminResponse;
}

