package com.project.backend.admin.service;

import java.util.List;

import com.project.backend.admin.dto.AdminVehicleRowDTO;
import com.project.backend.admin.dto.AdminVehicleStatsDTO;

public interface AdminVehicleService {

    AdminVehicleStatsDTO getVehicleStats();
    
    List<AdminVehicleRowDTO> getAllVehicles();
    
    void approveVehicle(Long vehicleId);

    void rejectVehicle(Long vehicleId);
}
