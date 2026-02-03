package com.project.backend.booking.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.project.backend.user.model.User;
import com.project.backend.vehicle.entities.Vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookings")
@Getter 
@Setter
@NoArgsConstructor
@ToString(exclude = {"customer", "vehicle"})
public class Booking {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(name = "pickup_datetime")
    private LocalDateTime pickupDateTime;

    @Column(name = "dropoff_datetime")
    private LocalDateTime dropoffDateTime;
    
    @Column(name = "total_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal  totalAmount;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
	public Booking(User customer, Vehicle vehicle, BookingStatus status, LocalDateTime startDate,
			LocalDateTime endDate, BigDecimal totalAmount) {
		super();
		this.customer = customer;
		this.vehicle = vehicle;
		this.status = status;
		this.pickupDateTime = startDate;
		this.dropoffDateTime = endDate;
		this.totalAmount = totalAmount;
	}
	
    

}
