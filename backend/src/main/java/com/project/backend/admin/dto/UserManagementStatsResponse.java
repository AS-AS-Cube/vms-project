package com.project.backend.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserManagementStatsResponse {

    private long totalCustomers;
    private long totalVendors;
    private long pendingApprovals;
    private long activeUsers;
}
