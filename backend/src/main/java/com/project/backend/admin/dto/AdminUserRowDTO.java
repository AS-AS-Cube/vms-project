package com.project.backend.admin.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AdminUserRowDTO {

    private Long userId;
    private String name;
    private String email;
    private String mobileNo;

    private String role;     
    private String status;  

    private Long activityCount;
    private Double activityAmount;

    private LocalDateTime joinedDate;
}
