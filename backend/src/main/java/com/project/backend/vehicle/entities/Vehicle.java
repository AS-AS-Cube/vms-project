package com.project.backend.vehicle.entities;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import com.project.backend.vendor.entities.Vendor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id")
	private Long vehicleId;
	
	@ManyToOne
	@JoinColumn(name = "vendor_id",nullable = false)
	private Vendor vendorId;
	
	@Column
	private String name;
	
	@Column
	private String brand;
	
	@Column(name = "model_year")
	private Integer modelYear;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type",nullable = false)
	private VehicleType vehicleType;
	
	@Column(name = "fuel_type")
	private String fuelType;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
	private Transmission transmission;
	
	@Column(name= "price_per_hour")
	private BigDecimal pricePerHour;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "availability_status",nullable = false)
	private AvailabilityStatus availabilityStatus;
	
	@ManyToOne
	@JoinColumn(name = "location_id",nullable = false)
	private Location location;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
    private LocalDateTime updatedAt;
 
	@Column(name = "deleted_at")
    private LocalDateTime deletedAt;
	
	@Lob
 	private byte[] image;

	public Vehicle(Vendor vendorId, String name, String brand, Integer modelYear, VehicleType vehicleType,
			String fuelType, Transmission transmission, BigDecimal pricePerHour, AvailabilityStatus availabilityStatus,
			Location locationId) {
		super();
		this.vendorId = vendorId;
		this.name = name;
		this.brand = brand;
		this.modelYear = modelYear;
		this.vehicleType = vehicleType;
		this.fuelType = fuelType;
		this.transmission = transmission;
		this.pricePerHour = pricePerHour;
		this.availabilityStatus = availabilityStatus;
		this.location = locationId;
	}
	
	
}
