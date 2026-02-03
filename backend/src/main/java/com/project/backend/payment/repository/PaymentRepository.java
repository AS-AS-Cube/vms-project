package com.project.backend.payment.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.backend.payment.entities.Payment;
import com.project.backend.payment.entities.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
        SELECT COALESCE(SUM(p.amount), 0)
        FROM Payment p
        WHERE p.status = :status
          AND p.paymentDate >= :start
          AND p.paymentDate < :end
    """)
    double sumAmountBetween(
            @Param("status") PaymentStatus status,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
    @Query("""
    	    SELECT COALESCE(SUM(p.amount), 0)
    	    FROM Payment p
    	    WHERE p.booking.vehicle.vendorId.user.userId = :userId
    	      AND p.status = :status
    	""")
    	Double vendorRevenue(@Param("userId") Long userId,
    	                     @Param("status") PaymentStatus status);

}
