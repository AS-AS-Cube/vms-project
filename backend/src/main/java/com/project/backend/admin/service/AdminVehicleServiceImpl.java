package com.project.backend.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.backend.admin.dto.AdminVehicleRowDTO;
import com.project.backend.admin.dto.AdminVehicleStatsDTO;
import com.project.backend.vehicle.entities.AvailabilityStatus;
import com.project.backend.vehicle.entities.Vehicle;
import com.project.backend.vehicle.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminVehicleServiceImpl implements AdminVehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public AdminVehicleStatsDTO getVehicleStats() {

        long totalVehicles =
                vehicleRepository.countAllActiveVehicles();

        long activeVehicles =
                vehicleRepository.countByAvailabilityStatusForAdmin(
                        AvailabilityStatus.AVAILABLE);


        long vehiclesInMaintenance =
                vehicleRepository.countByAvailabilityStatusForAdmin(
                        AvailabilityStatus.MAINTENANCE);

        return AdminVehicleStatsDTO.builder()
                .totalVehicles(totalVehicles)
                .activeVehicles(activeVehicles)
                .vehiclesInMaintenance(vehiclesInMaintenance)
                .build();
    }
    
    @Override
    public List<AdminVehicleRowDTO> getAllVehicles() {

        return vehicleRepository.findAll()
            .stream()
            .map(this::mapToDTO)
            .toList();
    }
    private AdminVehicleRowDTO mapToDTO(Vehicle v) {

        return AdminVehicleRowDTO.builder()
            .vehicleId(v.getVehicleId())
            .name(v.getName())
            .modelYear(v.getModelYear())
            .vehicleType(v.getVehicleType().name())

            .availabilityStatus(v.getAvailabilityStatus().name())

            .vendorName(v.getVendorId().getCompanyName())
            .fuelType(v.getFuelType())
            .transmission(v.getTransmission().name())

            .pricePerHour(v.getPricePerHour())
            .locationName(v.getLocation().getCity())

            // dummy values for now (future: review table)
            .rating(4.5)
            .reviewCount(12)

            .build();
    }
    
    @Override
    public void approveVehicle(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // APPROVE = ACTIVE + APPROVED
        vehicle.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);

        vehicleRepository.save(vehicle);
    }

    @Override
    public void rejectVehicle(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // REJECT = INACTIVE + REJECTED
        vehicle.setAvailabilityStatus(AvailabilityStatus.INACTIVE);

        vehicleRepository.save(vehicle);
    }
}

