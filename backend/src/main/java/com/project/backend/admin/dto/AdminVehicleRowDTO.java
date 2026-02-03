
package com.project.backend.admin.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminVehicleRowDTO {

    private Long vehicleId;
    private String name;
    private Integer modelYear;
    private String vehicleType;

    private String availabilityStatus;

    private String vendorName;
    private String fuelType;
    private String transmission;

    private BigDecimal pricePerHour;
    private String locationName;

    private Double rating;
    private Integer reviewCount;
}
