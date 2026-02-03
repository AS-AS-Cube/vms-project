package com.project.backend.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DashboardStatsResponse {

    private long totalUsers;
    

    private long activeVehicles;
 

    private double monthlyRevenue;
   

    private long activeBookings;

}

