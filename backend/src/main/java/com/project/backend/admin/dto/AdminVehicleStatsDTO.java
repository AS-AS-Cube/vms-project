package com.project.backend.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminVehicleStatsDTO {

    private long totalVehicles;
    private long activeVehicles;
    private long vehiclesInMaintenance;
}
