package com.project.backend.admin.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComplaintDetailsDTO {
    private Long complaintId;
    private String subject;
    private String description;
    private String status;

    private String userName;
    private String userRole;
    private String email;
    private String phone;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String relatedTo;

    private String adminResponse;
    private LocalDateTime respondedAt;
}
