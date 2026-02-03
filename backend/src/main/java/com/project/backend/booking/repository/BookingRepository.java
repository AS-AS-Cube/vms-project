package com.project.backend.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.backend.booking.entities.Booking;
import com.project.backend.booking.entities.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // ✅ Active bookings count
    long countByStatusIn(List<BookingStatus> statuses);

    // ✅ Used in AdminUserServiceImpl
    @Query("""
        SELECT COUNT(b)
        FROM Booking b
        WHERE b.customer.userId = :userId
    """)
    Long countBookingsByUser(@Param("userId") Long userId);

    @Query("""
        SELECT COALESCE(SUM(b.totalAmount), 0)
        FROM Booking b
        WHERE b.customer.userId = :userId
    """)
    Double sumBookingAmountByUser(@Param("userId") Long userId);
    
    @Query("""
            SELECT COUNT(b)
            FROM Booking b
            WHERE b.status IN ('CONFIRMED','PENDING')
        """)
        long countActiveBookings();

        @Query("""
            SELECT COUNT(b)
            FROM Booking b
            WHERE b.status = 'PENDING'
        """)
        long countPendingApprovals();


        @Query("""
            SELECT b
            FROM Booking b
            ORDER BY b.createdAt DESC
        """)
        List<Booking> findAllForAdmin();
}
