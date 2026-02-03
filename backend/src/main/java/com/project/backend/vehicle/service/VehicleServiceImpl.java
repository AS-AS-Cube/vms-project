package com.project.backend.vehicle.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.vehicle.entities.AvailabilityStatus;
import com.project.backend.vehicle.repository.VehicleRepository;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public long countActiveVehicles() {
        return vehicleRepository.countByStatus(AvailabilityStatus.AVAILABLE);
    }

    
}
